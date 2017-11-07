package hellojava.concurrent.lesson02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Tykkidream on 2017/11/5.
 */
public class ReadWriteDemo {
    private static Logger logger = LoggerFactory.getLogger(ReadReadDemo.class);

    public static class ReentrantReadWriteLock_Public extends ReentrantReadWriteLock {
        @Override
        public Collection<Thread> getQueuedThreads() {
            return super.getQueuedThreads();
        }

        @Override
        protected Collection<Thread> getQueuedReaderThreads() {
            return super.getQueuedReaderThreads();
        }

        @Override
        protected Collection<Thread> getQueuedWriterThreads() {
            return super.getQueuedWriterThreads();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock_Public readWriteLock = new ReentrantReadWriteLock_Public();

        // 试验读读共享的情况
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

        ReadLockBusinessThread thread1 = new ReadLockBusinessThread(readLock);
        ReadLockBusinessThread thread2 = new ReadLockBusinessThread(readLock);
        ReadLockBusinessThread thread3 = new ReadLockBusinessThread(readLock);
        ReadLockBusinessThread thread4 = new ReadLockBusinessThread(readLock);
        ReadLockBusinessThread thread5 = new ReadLockBusinessThread(readLock);


        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        WriteLockBusinessThread thread6 = new WriteLockBusinessThread(writeLock);

        thread1.start();
        thread2.start();

        thread6.start();

        Thread.sleep(100);

        logger.info("可能正在等待获取【读取】锁的线程：{}", readWriteLock.getQueuedReaderThreads());
        logger.info("可能正在等待获取【写入】锁的线程：{}", readWriteLock.getQueuedWriterThreads());

        thread3.start();
        thread4.start();
        thread5.start();

        Thread.sleep(100);

        logger.info("可能正在等待获取【读取】锁的线程：{}", readWriteLock.getQueuedReaderThreads());
        logger.info("可能正在等待获取【写入】锁的线程：{}", readWriteLock.getQueuedWriterThreads());
    }

    public static class ReadLockBusinessThread extends Thread {
        private static Logger logger = LoggerFactory.getLogger(ReadReadDemo.LockBusinessThread.class);

        private Lock lock;

        public ReadLockBusinessThread(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            // 读锁，多个线程同时都可以抢到锁。
            lock.lock();

            try {
                logger.info("线程 {} 抢到到【读】锁！", currentThread().getName());
                Thread.sleep(2000);
            } catch (Throwable throwable) {
                logger.error(throwable.getMessage(), throwable);
            } finally {
                logger.info("线程 {} 处理完成！", currentThread().getName());
                lock.unlock();
            }
        }
    }

    public static class WriteLockBusinessThread extends Thread {
        private static Logger logger = LoggerFactory.getLogger(ReadReadDemo.LockBusinessThread.class);

        private Lock lock;

        public WriteLockBusinessThread(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            // 读锁，多个线程同时都可以抢到锁。
            lock.lock();

            try {
                logger.info("线程 {} 抢到到【写】锁！", currentThread().getName());
                Thread.sleep(2000);
            } catch (Throwable throwable) {
                logger.error(throwable.getMessage(), throwable);
            } finally {
                logger.info("线程 {} 处理完成！", currentThread().getName());
                lock.unlock();
            }
        }
    }

}
