package hellojava.v21.concurrent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;

public class Demo06 {

    private static final Logger logger = LoggerFactory.getLogger(Demo06.class);

    /**
     * 使用工厂创建虚拟线程
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadFactory factory = Thread.ofVirtual().name("Thread.ofVirtual", 0).factory();

        Thread thread = factory.newThread(() -> {
            var current = Thread.currentThread();
            logger.info("在真实线程中执行开始： {}", current);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logger.info("在虚拟线程中执行结束");
        });

        thread.start();

        logger.info("开始");

        Thread.sleep(1000);
        logger.info("中间");
        Thread.sleep(1000);

        logger.info("结束");

    }
}