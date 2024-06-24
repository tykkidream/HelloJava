package hellojava.concurrent.lesson01.reentrant;

import hellojava.concurrent.lesson01.SyncBusinessEntity;
import hellojava.concurrent.lesson01.SyncBusinessThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tykkidream on 2017/10/24.
 */
public class ReentrantLockDemo1 {
    private static Logger logger = LoggerFactory.getLogger(ReentrantLockDemo1.class);

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        CountDownLatch latch = new CountDownLatch(2);

        SyncBusinessEntity entity = new SyncBusinessEntity();

        SyncBusinessThreadReform thread1 = new SyncBusinessThreadReform(lock, latch, entity, 1, 5);
        SyncBusinessThreadReform thread2 = new SyncBusinessThreadReform(lock, latch, entity, 2, 5);

        thread1.start();
        thread2.start();

        // 等所有线程都执行完
        latch.await();

        logger.info("最终结果 共 {} 个元素：{}", entity.size(), entity.toString());
    }

    public static class SyncBusinessThreadReform extends SyncBusinessThread {

        public SyncBusinessThreadReform(Lock lock, CountDownLatch latch, SyncBusinessEntity entity, int key, int maxValue) {
            super(lock, latch, entity, key, maxValue);
        }

        @Override
        public void run() {
            // 使用lock()获取锁
            lock.lock();

            try {
                doRun();
            } catch (Throwable throwable){
                logger.error(throwable.getMessage(), throwable);
            } finally {
                latch.countDown();
            }
        }
    }

}
