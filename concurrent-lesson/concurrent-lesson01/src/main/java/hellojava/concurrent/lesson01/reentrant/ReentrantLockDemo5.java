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
public class ReentrantLockDemo5 {
    private static Logger logger = LoggerFactory.getLogger(ReentrantLockDemo5.class);

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        CountDownLatch latch = new CountDownLatch(2);

        SyncBusinessEntity entity = new SyncBusinessEntity();

        SyncBusinessThreadReform thread1 = new SyncBusinessThreadReform(lock, latch, entity, 1, 5);
        SyncBusinessThreadReform thread2 = new SyncBusinessThreadReform(lock, latch, entity, 2, 5);

        thread1.start();
        thread2.start();

        // 统计执行当中，锁使用的情况
        logger.info("### 锁是否被抢：{}", lock.isLocked());
        logger.info("### 锁是否公平：{}", lock.isFair());
        logger.info("### 是否有线程正在等待获取此锁：{}", lock.hasQueuedThreads());
        logger.info("### 正在等待获取此锁的线程数量：{}", lock.getQueueLength());
        logger.info("### 线程1 是否正在等待获取此锁：{}", lock.hasQueuedThread(thread1));
        logger.info("### 线程2 是否正在等待获取此锁：{}", lock.hasQueuedThread(thread2));

        latch.await();

        logger.info("最终结果 共 {} 个元素：{}", entity.size(), entity.toString());
    }

    public static class SyncBusinessThreadReform extends SyncBusinessThread {

        public SyncBusinessThreadReform(Lock lock, CountDownLatch latch, SyncBusinessEntity entity, int key, int maxValue) {
            super(lock, latch, entity, key, maxValue);
        }

        @Override
        public void run() {
            lock.lock();

            try {
                ReentrantLock reentrantLock = (ReentrantLock)lock;
                logger.info("*** 当前线程持有锁的次数：{}", reentrantLock.getHoldCount());

                doRun();
            } catch (Throwable throwable){
                logger.error(throwable.getMessage(), throwable);
            } finally {
                latch.countDown();
            }
        }
    }
}
