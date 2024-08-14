package hellojava.v21.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo02 {

    private static final Logger logger = LoggerFactory.getLogger(Demo02.class);

    /**
     * 真实线程的新接口
     *
     * 从日志输出上可以看到 new Thread() 和 Thread.ofPlatform() 创建的线程名称是同一风格的，所以可以得出两者使用的是相同的线程技术。
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                logger.info("在传统线程中执行");
            }
        };

        Thread.ofPlatform().start(() -> {
            var current = Thread.currentThread();
            logger.info("在真实线程中执行开始： {}", current);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logger.info("在真实线程中执行结束");
        });

        logger.info("开始");

        thread.start();

        Thread.sleep(1000);
        logger.info("中间");
        Thread.sleep(1000);

        logger.info("结束");

    }
}
