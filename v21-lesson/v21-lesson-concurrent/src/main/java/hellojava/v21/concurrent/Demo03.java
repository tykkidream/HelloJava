package hellojava.v21.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;

public class Demo03 {

    private static final Logger logger = LoggerFactory.getLogger(Demo03.class);

    /**
     * 简单使用虚拟线程池
     *
     * 看日志输出与 Thread.ofVirtual() 的方式地日志一模一样，说明与使用 Thread.ofVirtual() 没区别。
     *
     * 实际情况下直接使用 Thread.ofVirtual() 即可，没必要使用 Executors.newVirtualThreadPerTaskExecutor()。
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        var executorService = Executors.newVirtualThreadPerTaskExecutor();

        logger.info("开始");

        executorService.submit(() -> {
            var current = Thread.currentThread();
            logger.info("在虚拟线程中执行开始： {}", current);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logger.info("在虚拟线程中执行结束");
        });

        Thread.sleep(1000);
        logger.info("中间");
        Thread.sleep(1000);

        logger.info("结束");

        executorService.close();
    }
}
