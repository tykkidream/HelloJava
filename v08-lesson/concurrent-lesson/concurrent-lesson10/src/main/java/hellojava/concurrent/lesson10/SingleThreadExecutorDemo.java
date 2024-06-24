package hellojava.concurrent.lesson10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorDemo {
    private static final Logger logger = LoggerFactory.getLogger(SingleThreadExecutorDemo.class);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Runnable runnable = () -> {
            String uuid = UUID.randomUUID().toString();
            logger.warn("线程被运行啦！{} 当前线程名为 {}", uuid, Thread.currentThread().getName());
            logger.warn("线程被运行啦！{} 当前线程名为 {}", uuid, Thread.currentThread().getName());
            logger.warn("线程被运行啦！{} 当前线程名为 {}", uuid, Thread.currentThread().getName());
        };

        logger.warn("提交一次！");
        executorService.execute(runnable);
        logger.warn("提交一次！");
        executorService.execute(runnable);
        logger.warn("提交一次！");
        executorService.execute(runnable);


        logger.warn("关闭线程池！");

        executorService.shutdown();
    }
}
