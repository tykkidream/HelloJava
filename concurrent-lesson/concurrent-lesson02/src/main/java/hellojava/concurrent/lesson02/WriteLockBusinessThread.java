package hellojava.concurrent.lesson02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;

/**
 * Created by Tykkidream on 2017/11/7.
 */
public class WriteLockBusinessThread extends Thread {
    private static Logger logger = LoggerFactory.getLogger(WriteLockBusinessThread.class);

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