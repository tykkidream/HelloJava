package hellojava.v21.concurrent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo01 {

    private static final Logger logger = LoggerFactory.getLogger(Demo01.class);

    /**
     * 简单使用虚拟线程
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.ofVirtual().start(() -> {
            var current = Thread.currentThread();
            logger.info("在真实线程中执行开始： {}", current);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logger.info("在虚拟线程中执行结束");
        });

        logger.info("开始");

        Thread.sleep(1000);
        logger.info("中间");
        Thread.sleep(1000);

        logger.info("结束");

    }
}
