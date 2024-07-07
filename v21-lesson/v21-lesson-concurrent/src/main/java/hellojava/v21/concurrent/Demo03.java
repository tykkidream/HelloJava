package hellojava.v21.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo03 {

    private static final Logger logger = LoggerFactory.getLogger(Demo03.class);

    /**
     * 简单使用虚拟线程池
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        var executorService = Executors.newVirtualThreadPerTaskExecutor();

        logger.info("开始");

        executorService.submit(() -> {
            var current = Thread.currentThread();
            logger.info("在真实线程中执行开始： {}", current);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logger.info("在真实线程中执行结束");
        });

        Thread.sleep(1000);
        logger.info("中间");
        Thread.sleep(1000);

        logger.info("结束");

        executorService.close();
    }
}
