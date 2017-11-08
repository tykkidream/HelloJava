package hellojava.concurrent.lesson03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo2 {
    private static Logger logger = LoggerFactory.getLogger(LockSupportDemo2.class);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                logger.info("子线程开始");
                // 等待或许许可
                // 可被 thread.interrupt() 中断
                LockSupport.park();

                logger.info("测试线程结束：{}",Thread.currentThread().isInterrupted());

            }
        };

        logger.info("开始测试");

        thread.start();

        Thread.sleep(3000);

        // 中断线程
        thread.interrupt();

        Thread.sleep(1000);
        logger.info("主线程结束");
    }
}
