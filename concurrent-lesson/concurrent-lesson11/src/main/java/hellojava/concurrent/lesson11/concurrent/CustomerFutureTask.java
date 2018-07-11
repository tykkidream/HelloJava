package hellojava.concurrent.lesson11.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.concurrent.FutureTask;

public class CustomerFutureTask extends FutureTask {
    private static final Logger logger = LoggerFactory.getLogger(CustomerFutureTask.class);

    public CustomerFutureTask(Runnable runnable, Object result) {
        super(runnable, result);
    }

    public void killThread() {
        try {
            // 利用反射，强行取出正在运行该任务的线程
            Field runner = this.getClass().getDeclaredField("runner");
            runner.setAccessible(true);
            Thread execThread = (Thread) runner.get(this);
            execThread.stop();
            execThread = null;
            // 为了防止句柄泄漏，这里催促jvm进行gc回收，那是因为进行gc回收时，可以收回被stop的线程所占用的句柄
            System.gc();
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
        }
    }
}
