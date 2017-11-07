package hellojava.concurrent.lesson02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Tykkidream on 2017/11/5.
 */
public class ReadWriteDemo {

    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

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

        thread3.start();
        thread4.start();
        thread5.start();
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
                logger.info(String.format("线程 %s 抢到到【读】锁！", currentThread().getName()));
                Thread.sleep(1000);
            } catch (Throwable throwable) {
                logger.error(throwable.getMessage(), throwable);
            } finally {
                logger.info(String.format("线程 %s 处理完成！", currentThread().getName()));
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
                logger.info(String.format("线程 %s 抢到到【写】锁！", currentThread().getName()));
                Thread.sleep(1000);
            } catch (Throwable throwable) {
                logger.error(throwable.getMessage(), throwable);
            } finally {
                logger.info(String.format("线程 %s 处理完成！", currentThread().getName()));
                lock.unlock();
            }
        }
    }

}
