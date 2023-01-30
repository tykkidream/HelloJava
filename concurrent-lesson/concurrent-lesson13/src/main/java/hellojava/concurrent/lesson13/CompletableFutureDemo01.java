package hellojava.concurrent.lesson13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo01 {

    private static final Logger logger = LoggerFactory.getLogger(CompletableFutureDemo01.class);

    public static void main(String[] args) throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureDemo01::fetchPrice);

        logger.info("设置执行成功如何处理 begin");

        // 如果执行成功:
        cf.thenAccept((result) -> {
            logger.info("成功获取到价格 price: " + result);
        });

        logger.info("设置执行成功如何处理 end");

        logger.info("设置执行失败如何处理 begin");
        // 如果执行异常:
        cf.exceptionally((e) -> {
            logger.error("失败了", e);
            return null;
        });
        logger.info("设置执行失败如何处理 end");

        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static Double fetchPrice() {
        logger.info("\t\t开始执行");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }

        logger.info("\t\t结束执行");

        return 5 + Math.random() * 20;
    }
}
