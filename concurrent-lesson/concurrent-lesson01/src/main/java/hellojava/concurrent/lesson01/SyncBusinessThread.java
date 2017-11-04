package hellojava.concurrent.lesson01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/**
 * Created by Tykkidream on 2017/10/29.
 */
public class SyncBusinessThread extends Thread {
    private static Logger logger = LoggerFactory.getLogger(SyncBusinessThread.class);

    protected Lock lock;
    protected CountDownLatch latch;
    protected SyncBusinessEntity entity;
    protected int key;
    protected int maxValue;

    public SyncBusinessThread(Lock lock, CountDownLatch latch, SyncBusinessEntity entity, int key, int maxValue) {
        this.lock = lock;
        this.latch = latch;
        this.entity = entity;
        this.key = key;
        this.maxValue = maxValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < maxValue; i++) {
            entity.add(Thread.currentThread().getName(), key, i);

            logger.info("{} 添加了 {} , {}， 共 {} 个 ：{}", Thread.currentThread().getName(), key, i, entity.size(), entity.toString());
        }
        latch.countDown();
    }

    protected void doRun(){
        try {
            for (int i = 0; i < maxValue; i++) {
                entity.add(Thread.currentThread().getName(), key, i);

                logger.info("{} 添加了 {} , {}， 共 {} 个 ：{}", Thread.currentThread().getName(), key, i, entity.size(), entity.toString());
            }
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            // 使用unlock()释放锁
            lock.unlock();
        }
    }
}
