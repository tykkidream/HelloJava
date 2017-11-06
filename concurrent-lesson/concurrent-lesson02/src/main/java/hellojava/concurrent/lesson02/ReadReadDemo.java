package hellojava.concurrent.lesson02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Tykkidream on 2017/11/5.
 */
public class ReadReadDemo {

    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        // 试验读读共享的情况
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

        LockBusinessThread thread1 = new LockBusinessThread(readLock);
        LockBusinessThread thread2 = new LockBusinessThread(readLock);
        LockBusinessThread thread3 = new LockBusinessThread(readLock);
        LockBusinessThread thread4 = new LockBusinessThread(readLock);
        LockBusinessThread thread5 = new LockBusinessThread(readLock);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

    public static class LockBusinessThread extends Thread {
        private static Logger logger = LoggerFactory.getLogger(LockBusinessThread.class);

        private Lock lock;

        public LockBusinessThread(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            // 读锁，多个线程同时都可以抢到锁。
            lock.lock();

            try {
                logger.info(String.format("线程 %s 抢到到锁！", currentThread().getName()));
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
