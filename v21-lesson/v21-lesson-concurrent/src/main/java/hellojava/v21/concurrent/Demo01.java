package hellojava.v21.concurrent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo01 {

    private static final Logger logger = LoggerFactory.getLogger(Demo01.class);

    /**
     * 简单使用虚拟线程
     *
     * 日志中输出了：[virtual-22] [] INFO  hellojava...Demo01 - 在虚拟线程中执行开始： VirtualThread[#22]/runnable@ForkJoinPool-1-worker-1
     *
     * 表示虚拟线程号是 virtual-22 ，运行在了真实线程 worker-1 上。
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.ofVirtual().start(() -> {
            var current = Thread.currentThread();
            logger.info("在虚拟线程中执行开始： {}", current);
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
