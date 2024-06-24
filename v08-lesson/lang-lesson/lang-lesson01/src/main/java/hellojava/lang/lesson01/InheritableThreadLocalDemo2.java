package hellojava.lang.lesson01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InheritableThreadLocalDemo2 implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(InheritableThreadLocalDemo2.class);

    private static final InheritableThreadLocal threadLocal = new InheritableThreadLocal();

    /**
     * 这个Demo主要想说明线程池中的线程一开始也是从主线程中继承值，但由于线程复用，之后这个值就会被不同的任务中传递。
     * @param args
     */
    public static void main(String[] args) {
        String value = UUID.randomUUID().toString();
        threadLocal.set(value);

        logger.info("主线程 "  + threadLocal.get());

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.submit(new InheritableThreadLocalDemo2("一线程"));
        executorService.submit(new InheritableThreadLocalDemo2("二线程"));

        logger.info("主线程 开始休眠1秒");
        sleep(1000L);
        logger.info("主线程 结束休眠");
        executorService.submit(new InheritableThreadLocalDemo2("三线程"));

        logger.info("主线程 开始休眠1秒");
        sleep(1000L);
        logger.info("主线程 结束休眠");

        logger.info("主线程 "  + value);

        executorService.submit(new InheritableThreadLocalDemo2("四线程"));
        value = UUID.randomUUID().toString();

        logger.info("主线程 修改值");
        threadLocal.set(value);
        logger.info("主线程 "  + threadLocal.get());

        executorService.submit(new InheritableThreadLocalDemo2("五线程"));
        executorService.submit(new InheritableThreadLocalDemo2("六线程"));

        logger.info("主线程 开始休眠1秒");
        sleep(1000L);
        logger.info("主线程 结束休眠");

        logger.info("主线程 "  + threadLocal.get());

        executorService.shutdown();
    }

    private String name;

    public InheritableThreadLocalDemo2(String name){
        this.name = name;
    }

    public void run() {
        logger.info(name + " " + threadLocal.get());
        logger.info(name + " 设置值");
        threadLocal.set(UUID.randomUUID().toString());
        logger.info(name + " " + threadLocal.get());
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
