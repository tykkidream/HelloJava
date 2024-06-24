package hellojava.concurrent.lesson10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledThreadPoolDemo.class);

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executorService = (ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(2);

        logger.warn("提交一次！");
        executorService.scheduleAtFixedRate(new MyRunnable(), 1000, 2000, TimeUnit.MILLISECONDS);
        logger.warn("提交一次！");
        executorService.scheduleAtFixedRate(new MyRunnable(), 1000, 2000, TimeUnit.MILLISECONDS);
        logger.warn("提交一次！");
        executorService.scheduleAtFixedRate(new MyRunnable(), 1000, 2000, TimeUnit.MILLISECONDS);


        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        logger.warn("关闭线程池！");

        executorService.shutdown();
    }

    private static class MyRunnable implements Runnable {
        private String uuid = UUID.randomUUID().toString();
        @Override
        public void run() {
            logger.warn("线程被运行啦！{} 当前线程名为 {}", uuid, Thread.currentThread().getName());
            logger.warn("线程被运行啦！{} 当前线程名为 {}", uuid, Thread.currentThread().getName());
            logger.warn("线程被运行啦！{} 当前线程名为 {}", uuid, Thread.currentThread().getName());
        }
    }
}
