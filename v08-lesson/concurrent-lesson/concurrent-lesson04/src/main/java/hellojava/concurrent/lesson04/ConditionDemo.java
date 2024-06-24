package hellojava.concurrent.lesson04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tykkidream on 2017/11/9.
 */
public class ConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

        AwaitThread awaitThread = new AwaitThread(condition, lock);

        SignalThread signalThread = new SignalThread(condition, lock);

        awaitThread.start();

        Thread.sleep(1000);

        signalThread.start();
    }

    private static class AwaitThread extends Thread {
        private static Logger logger = LoggerFactory.getLogger(AwaitThread.class);

        Condition condition;
        ReentrantLock lock;

        AwaitThread(Condition condition, ReentrantLock lock) {
            this.condition = condition;
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();

            try {
                logger.info("{} 开始处理，当前是否有锁 {}", getName(), lock.isHeldByCurrentThread());
                condition.await();
                logger.info("{} 完成处理，当前是否有锁 {}", getName(), lock.isHeldByCurrentThread());
            } catch (Throwable throwable) {
                logger.error(throwable.getMessage(), throwable);
            } finally {
                lock.unlock();
            }
        }
    }

    private static class SignalThread extends Thread{
        private static Logger logger = LoggerFactory.getLogger(SignalThread.class);

        Condition condition;
        ReentrantLock lock;

        SignalThread(Condition condition, ReentrantLock lock) {
            this.condition = condition;
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();

            try {
                logger.info("{} 开始处理，当前是否有锁 {}", getName(), lock.isHeldByCurrentThread());
                condition.signal();
                logger.info("{} 完成处理，当前是否有锁 {}", getName(), lock.isHeldByCurrentThread());
            } catch (Throwable throwable) {
                logger.error(throwable.getMessage(), throwable);
            } finally {
                lock.unlock();
            }
        }
    }
}
