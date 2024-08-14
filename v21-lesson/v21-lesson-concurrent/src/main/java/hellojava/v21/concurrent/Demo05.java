package hellojava.v21.concurrent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo05 {

    private static final Logger logger = LoggerFactory.getLogger(Demo05.class);

    /**
     * 为虚拟线程起名字
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.ofVirtual().name("自定义Thread.ofVirtual名称前缀-", 0).start(() -> {
            var current = Thread.currentThread();
            logger.info("在虚拟线程中执行开始： {}", current.getName());
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
