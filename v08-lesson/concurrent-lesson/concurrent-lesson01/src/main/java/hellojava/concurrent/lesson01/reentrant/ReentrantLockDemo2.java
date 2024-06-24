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
        public SyncBusinessThreadReform(Lock lock, CountDownLatch latch, SyncBusinessEntity entity, int key, int maxValue) {
            super(lock, latch, entity, key, maxValue);
        }

        @Override
        public void run() {
            try {
                // 获取锁，当线程被中断时，抛出异常，终止方法。
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                logger.warn("{} 获取锁失败", currentThread().getName());
                logger.error(e.getMessage(), e);
                latch.countDown();
                return;
            }

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
