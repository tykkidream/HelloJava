package hellojava.lang.lesson01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class InheritableThreadLocalDemo1 {
    private static Logger logger = LoggerFactory.getLogger(InheritableThreadLocalDemo1.class);

    private static final InheritableThreadLocal threadLocal = new InheritableThreadLocal();

    /**
     * 此Demo演示了InheritableThreadLocal基本用法和特性：
     * 主线程首先有值，其子线程将继承其值，之后主线程对值的任何修改，子线程不可见，子线程对其值的修改，主线程也不可见。
     * @param args
     */
    public static void main(String[] args) {
        String value = UUID.randomUUID().toString();
        threadLocal.set(value);

        logger.info("主线程 "  + threadLocal.get());

        Thread thread = new Thread() {
            @Override
            public void run() {
                logger.info("一线程 " +threadLocal.get());
                logger.info("一线程 开始休眠2秒");

                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                logger.info("一线程 结束休眠");
                logger.info("一线程 " + threadLocal.get());

                logger.info("一线程 修改值");
                threadLocal.set("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
                logger.info("一线程 " +threadLocal.get());

                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        logger.info("二二线程 " + threadLocal.get());
                        logger.info("二二线程 设置值");
                        threadLocal.set("cccccccccccccccc");
                        logger.info("二二线程 " + threadLocal.get());

                        logger.info("二二线程 移除");
                        threadLocal.remove();

                        logger.info("二二线程 " + threadLocal.get());
                    }
                };

                logger.info("一线程 开始二二线程");
                thread.start();

                logger.info("一线程 开始休眠3秒");
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                logger.info("一线程 结束休眠");
                logger.info("一线程 " +threadLocal.get());
            }
        };

        logger.info("主线程 开始一线程");
        thread.start();

        logger.info("主线程 开始休眠1秒");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("主线程 结束休眠");
        logger.info("主线程 修改值");
        value = UUID.randomUUID().toString();
        threadLocal.set(value);
        logger.info("主线程 "  + threadLocal.get());

        logger.info("主线程 开始休眠6秒");
        try {
            Thread.sleep(6000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("主线程 结束休眠");
        logger.info("主线程 " + threadLocal.get());
    }
}
