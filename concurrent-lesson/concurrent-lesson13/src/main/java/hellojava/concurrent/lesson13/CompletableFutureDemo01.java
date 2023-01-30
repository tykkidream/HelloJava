package hellojava.concurrent.lesson13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo01 {

    private static final Logger logger = LoggerFactory.getLogger(CompletableFutureDemo01.class);

    public static void main(String[] args) throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureDemo01::fetchPrice);

        Double value;

        logger.info("=== 开始 ===");

        try {
            // 注意， CompletableFuture.get(time) 是不支持【指定超时时间内，没有值则返回null，不抛出异常】，会抛出异常的。
            value = cf.get(100, TimeUnit.MILLISECONDS);
            logger.info("通过 get(time) 获取值成功：" + value);
        } catch (Throwable throwable) {
            // 因为超时没有获取到值，会报异常！
            logger.error("通过 get(time) 获取值异常：", throwable);
        }

        logger.info("isCancelled             ：" + cf.isCancelled());
        logger.info("isDone                  ：" + cf.isDone());
        logger.info("isCompletedExceptionally：" + cf.isCompletedExceptionally());

        try {
            // 阻塞当前线程，直到获取到值。
            value = cf.get();
            logger.info("通过 get() 获取值成功：" + value);
        } catch (Throwable throwable) {
            // 如果 fetchPrice 抛出了异常，这里会捕获到异常。
            logger.error("通过 get() 获取值异常：", throwable);
        }

        logger.info("isCancelled             ：" + cf.isCancelled());
        logger.info("isDone                  ：" + cf.isDone());
        logger.info("isCompletedExceptionally：" + cf.isCompletedExceptionally());
    }

    /**
     * 在默认线程池中执行
     *
     * @return
     */
    static Double fetchPrice() {
        logger.info("\t\t开始执行");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }

        logger.info("\t\t结束执行");

        return 5 + Math.random() * 20;
    }
}
