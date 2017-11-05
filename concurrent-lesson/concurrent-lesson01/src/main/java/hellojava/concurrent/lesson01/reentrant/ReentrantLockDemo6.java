package hellojava.concurrent.lesson01.reentrant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tykkidream on 2017/10/24.
 */
public class ReentrantLockDemo6 {
    private static Logger logger = LoggerFactory.getLogger(ReentrantLockDemo6.class);

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        // 没有调用lock之前，hold count为0
        logger.info("当前线程获取锁的次数为： {}", lock.getHoldCount());

        // holdCount + 1
        lock.lock();
        logger.info("当前线程获取锁的次数为： {}", lock.getHoldCount());

        // holdCount + 1
        lock.lock();
        logger.info("当前线程获取锁的次数为： {}", lock.getHoldCount());

        // holdCount + 1
        lock.lock();
        logger.info("当前线程获取锁的次数为： {}", lock.getHoldCount());

        // holdCount - 1
        lock.unlock();
        logger.info("当前线程获取锁的次数为： {}", lock.getHoldCount());

        // holdCount - 1
        lock.unlock();
        logger.info("当前线程获取锁的次数为： {}", lock.getHoldCount());

        // holdCount - 1
        lock.unlock();
        logger.info("当前线程获取锁的次数为： {}", lock.getHoldCount());
    }

}
