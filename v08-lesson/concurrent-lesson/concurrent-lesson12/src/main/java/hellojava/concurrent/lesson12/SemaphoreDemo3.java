package hellojava.concurrent.lesson12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 学习信号量
 */
public class SemaphoreDemo3 {
    private static final Logger logger = LoggerFactory.getLogger(SemaphoreDemo3.class);

    private static final Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws Throwable {
        semaphore.release();
        logger.info("发送第一次信号");
        semaphore.release();
        logger.info("发送第二次信号");
        semaphore.release();
        logger.info("发送第三次信号");
        semaphore.release();
        logger.info("发送第四次信号");

        semaphore.acquire();
        logger.info("获取第一次信号");
        semaphore.acquire();
        logger.info("获取第二次信号");
        semaphore.acquire();
        logger.info("获取第三次信号");
        semaphore.acquire();
        logger.info("获取第四次信号");
        semaphore.acquire();
        logger.info("获取第五次信号");
        semaphore.acquire();
        logger.info("获取第六次信号");
    }
}
