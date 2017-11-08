package hellojava.concurrent.lesson03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo3 {
    private static Logger logger = LoggerFactory.getLogger(LockSupportDemo3.class);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                logger.info("子线程开始");
                logger.info("子线程开始 - step 1");
                // 等待或许许可
                // 不可重入
                LockSupport.park();

                logger.info("子线程开始 - step 2");
                LockSupport.park();

                logger.info("子线程开始 - step 3");
                LockSupport.park();

                logger.info("子线程结束");

            }
        };

        logger.info("主线程开始");

        thread.start();

        Thread.sleep(3000);
        LockSupport.unpark(thread);

        Thread.sleep(3000);
        LockSupport.unpark(thread);

        Thread.sleep(3000);
        LockSupport.unpark(thread);

        Thread.sleep(1000);
        logger.info("主线程结束");
    }
}
