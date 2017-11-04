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
public class ReentrantLockDemo2 {
    private static Logger logger = LoggerFactory.getLogger(ReentrantLockDemo2.class);

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        CountDownLatch latch = new CountDownLatch(2);

        SyncBusinessEntity entity = new SyncBusinessEntity();

        SyncBusinessThreadReform thread1 = new SyncBusinessThreadReform(lock, latch, entity, 1, 5);
        SyncBusinessThreadReform thread2 = new SyncBusinessThreadReform(lock, latch, entity, 2, 5);

        thread1.start();
        thread2.start();
        thread1.interrupt();
        latch.await();

        logger.info("最终结果 共 {} 个元素：{}", entity.size(), entity.toString());
    }

    public static class SyncBusinessThreadReform extends SyncBusinessThread {
        private Lock lock;

        public SyncBusinessThreadReform(Lock lock, CountDownLatch latch, SyncBusinessEntity entity, int key, int maxValue) {
            super(latch, entity, key, maxValue);
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
                return;
            } finally {
                latch.countDown();
            }

            try {
                super.run();
            } finally {
                lock.unlock();
            }
        }
    }
}