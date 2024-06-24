package hellojava.concurrent.lesson11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimeoutThreadPool {
    private static final Logger logger = LoggerFactory.getLogger(TimeoutThreadPool.class);

    private ExecutorService controlThreadPool;

    private ExecutorService workingThreadPool;

    private AtomicBoolean cancelOnTimeout = new AtomicBoolean(true);

    private AtomicBoolean killOnDeadline = new AtomicBoolean(true);

    public boolean getCancelOnTimeout() {
        return cancelOnTimeout.get();
    }

    public void setCancelOnTimeout(boolean value, boolean before) {
        cancelOnTimeout.compareAndSet(before, value);
    }

    public boolean getKillOnDeadline() {
        return killOnDeadline.get();
    }

    public void setKillOnDeadline(boolean value, boolean before) {
        killOnDeadline.compareAndSet(before, value);
    }

    public void startup(int corePoolSize, int maximumPoolSize, int keepAliveTime) {
        startup(corePoolSize, maximumPoolSize, keepAliveTime, Executors.defaultThreadFactory());
    }

    public void startup(int corePoolSize, int maximumPoolSize, int keepAliveTime, ThreadFactory threadFactory) {
        controlThreadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        workingThreadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(), threadFactory);
    }

    public void shutdown() {
        controlThreadPool.shutdown();
        workingThreadPool.shutdown();
    }

    public void shutdownNow() {
        controlThreadPool.shutdownNow();
        workingThreadPool.shutdownNow();
    }

    public Future<?> submit(String taskId, Runnable runnable, long timeout) {
        return submit(taskId, runnable, timeout, 0L);
    }

    public Future<?> submit(String taskId, Runnable runnable, long timeout, long deadline) {
        Callable<Object> callable = Executors.callable(runnable);
        return submit(taskId, callable, timeout, deadline);
    }

    public <T> Future<T> submit(String taskId, Callable<T> callable, long timeout) {
        return submit(taskId, callable, timeout, 0L);
    }

    public <T> Future<T> submit(String taskId, Callable<T> callable, long timeout, long deadline) {
        ControlTask task = new ControlTask(taskId, callable, timeout, deadline, workingThreadPool);
        return controlThreadPool.submit(task);
    }

    private class ControlTask implements Callable {
        private String taskId;
        private Callable callable;
        private long timeout;
        private long deadline;
        private ExecutorService executorService;
        private CountDownLatch cdl;

        public ControlTask(String taskId, Callable callable, long timeout, long deadline, ExecutorService executorService) {
            this.taskId = taskId;
            this.cdl = new CountDownLatch(1);
            this.callable = new Callable() {
                @Override
                public Object call() throws Exception {
                    try {
                        return callable.call();
                    } catch (Throwable throwable) {
                        throw throwable;
                    } finally {
                        cdl.countDown();
                    }
                }
            };
            this.timeout =timeout;
            this.deadline = deadline;
            this.executorService = executorService;
        }

        @Override
        public Object call() throws Exception {
            Future<?> future;
            if (callable != null) {
                future = executorService.submit(callable);
            } else {
                throw new RuntimeException("不存在可执行的Runnable或Callable对象，taskId=" + taskId);
            }

            long beginTime = System.currentTimeMillis();

            try {
                return future.get(timeout, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                logger.error("任务已被中断，taskId={}", taskId, e);
            } catch (ExecutionException e) {
                logger.error("任务执行异常，taskId={}：{}", taskId, e);
            } catch (TimeoutException e) {
                if (cancelOnTimeout.get()) {
                    future.cancel(true);
                    logger.error("任务执行超时，开始取消，taskId={}，timeout={}", taskId, timeout, e);
                } else {
                    logger.error("任务执行超时，taskId={}，timeout={}", taskId, timeout, e);
                }
            } catch (Throwable throwable) {
                logger.error("任务处理异常，taskId={}：{}", taskId, throwable.getMessage(), throwable);
            }

            if (killOnDeadline.get() && deadline > 0) {
                if (cdl.await(deadline, TimeUnit.MILLISECONDS)){
                    if (logger.isWarnEnabled()) {
                        long endTime = System.currentTimeMillis();
                        logger.warn("超时任务在最后的期限前执行完成，taskId={}，timeout={}，实际耗时={}", taskId, timeout, endTime - beginTime);
                    }
                    if (!future.isCancelled()) {
                        return future.get();
                    }
                } else {
                    logger.error("超时任务已经达到最后的期限，开始强制杀死线程，taskId={}", taskId);
                    killThread(taskId, (FutureTask)future);
                }
            }

            return null;
        }

        private void killThread(String taskId, FutureTask<?> submit) {
            try {
                // 利用反射，强行取出正在运行该任务的线程
                Field runner = submit.getClass().getDeclaredField("runner");
                runner.setAccessible(true);
                Thread execThread = (Thread) runner.get(submit);
                if (execThread == null) {
                    if (logger.isWarnEnabled()) {
                        logger.warn("超时任务的线程已不存在，taskId={}", taskId);
                    }
                    return;
                }
                execThread.interrupt();
                execThread.stop();
                execThread.suspend();
                execThread.resume();
                if (logger.isWarnEnabled()) {
                    logger.warn("成功杀死超时任务的线程，taskId={}，threadName={}", taskId, execThread.getName());
                }
                execThread = null;
                // 为了防止句柄泄漏，这里催促jvm进行gc回收，那是因为进行gc回收时，可以收回被stop的线程所占用的句柄
                System.gc();
            } catch (Throwable throwable) {
                logger.error("杀死超时任务的线程时异常，taskId={}：{}", taskId, throwable.getMessage(), throwable);
            }
        }
    }
}
