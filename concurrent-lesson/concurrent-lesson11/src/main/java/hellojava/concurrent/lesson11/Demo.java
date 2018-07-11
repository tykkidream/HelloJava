package hellojava.concurrent.lesson11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        TimeoutThreadPool timeoutThreadPool = new TimeoutThreadPool();

        // 将下面这一行注释或打开，可以看到不同的效果
        //timeoutThreadPool.setCancelOnTimeout(false, true);

        timeoutThreadPool.startup(2, 2, 30000, new DemoThreadFactory());

        Future<String> future1 = timeoutThreadPool.submit("task-test-001", new Callable<String>() {
            @Override
            public String call() {
                try {
                    logger.info("开始任务。。。耗时长的任务");
                    Thread.sleep(50000L);
                    logger.info("完成任务。。。耗时长的任务");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return UUID.randomUUID().toString();
            }
        }, 1000, 3000);

        Future<String> future2 = timeoutThreadPool.submit("task-test-002", new Callable<String>() {
            @Override
            public String call() {
                try {
                    logger.info("开始任务。。。");
                    Thread.sleep(3000L);
                    logger.info("完成任务。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return UUID.randomUUID().toString();
            }
        }, 1000, 3000);


        Thread.sleep(2000L);

        Callable<String> callable = new Callable() {
            @Override
            public String call() {
                try {
                    logger.info("开始任务。。。正常任务");
                    Thread.sleep(4000L);
                    logger.info("完成任务。。。正常任务");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return UUID.randomUUID().toString();
            }
        };

        Future<String> future3 = timeoutThreadPool.submit("task-test-003", callable, 3000, 3000);
        Future<String> future4 = timeoutThreadPool.submit("task-test-004", callable, 3000, 3000);

        Thread.sleep(6000L);

        // 如果配置了任务超时执行取消，那么当取消后，无法再获取到在任务最后期限内执行完的结果值。
        logger.info("future1 result = {}", future1.get());
        logger.info("future2 result = {}", future2.get());
        logger.info("future3 result = {}", future3.get());
        logger.info("future4 result = {}", future4.get());


        long begin = System.currentTimeMillis();
        Future<String> future5 = timeoutThreadPool.submit("task-test-004", callable, 5000, 3000);
        String future5Result = future5.get();
        long end = System.currentTimeMillis();
        logger.info("future5 result = {}, time={}", future5Result, end - begin);

        timeoutThreadPool.shutdown();
    }
}
