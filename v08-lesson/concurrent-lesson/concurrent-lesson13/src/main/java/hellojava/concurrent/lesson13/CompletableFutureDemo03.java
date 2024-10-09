package hellojava.concurrent.lesson13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo03 {

    private static final Logger logger = LoggerFactory.getLogger(CompletableFutureDemo03.class);

    public static void main(String[] args) throws Exception {

        CompletableFuture<Double> future = new CompletableFuture<>();

        // 在另一个线程中完成 CompletableFuture
        new Thread(() -> {
            // 模拟长时间运行的任务
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> 完成 <<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            // 手工设置结果数据
            future.complete(5 + Math.random() * 20);
        }).start();


        logger.info("===========================================================");

        getPrint(future);

        // 在 future 完成后处理结果
        future.thenAccept(result -> {
            // 重要注意事项：当前回调会在执行 complete 的线程中执行！！！
            logger.info("===========================================================");
            logger.info("thenAccept 中获取到结果 {}", result);
            getPrint(future);
        });

        // 等待 future 完成
        future.join();

        Thread.sleep(100);

        logger.info("===========================================================");

        getPrint(future);
    }

    private static void getPrint(CompletableFuture<Double> future) {
        try {
            // 注意， CompletableFuture.get(time) 是不支持【指定超时时间内，没有值则返回null，不抛出异常】，会抛出异常的。
            Double value = future.get(100, TimeUnit.MILLISECONDS);
            logger.info("通过 get(time) 获取值成功：" + value);
        } catch (Throwable throwable) {
            // 因为超时没有获取到值，会报异常！
            logger.error("通过 get(time) 获取值异常：", throwable);
        }

        logger.info("isCancelled             ：" + future.isCancelled());
        logger.info("isDone                  ：" + future.isDone());
        logger.info("isCompletedExceptionally：" + future.isCompletedExceptionally());
    }
}
