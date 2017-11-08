package hellojava.concurrent.lesson03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo4 {
    private static Logger logger = LoggerFactory.getLogger(LockSupportDemo4.class);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    logger.info("子线程开始");

                    // 先阻塞，等待释放
                    LockSupport.park();
                    logger.info("子线程开始 - step 1");

                    LockSupport.park();
                    logger.info("子线程开始 - step 2");

                    logger.info("==========================================");

                    Thread.sleep(2000);
                    // 提前被释放，此时不会阻塞
                    LockSupport.park();
                    logger.info("子线程开始 - step A");

                    Thread.sleep(2000);
                    LockSupport.park();
                    logger.info("子线程开始 - step B");

                    Thread.sleep(2000);
                    LockSupport.park();
                    logger.info("子线程开始 - step C");

                    logger.info("子线程结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        logger.info("主线程开始");

        thread.start();

        // 子线程先阻塞，主线程再释放
        Thread.sleep(3000);
        LockSupport.unpark(thread);

        Thread.sleep(3000);
        LockSupport.unpark(thread);

        logger.info("==========================================");

        // 主线程可以提前释放，子线程之后不会被阻塞
        Thread.sleep(1000);
        LockSupport.unpark(thread);
        logger.info("主线程开始 - step A");

        Thread.sleep(2000);
        LockSupport.unpark(thread);
        logger.info("主线程开始 - step B");

        Thread.sleep(2000);
        LockSupport.unpark(thread);
        logger.info("主线程开始 - step C");

        Thread.sleep(1000);
        logger.info("主线程结束");
    }
}
