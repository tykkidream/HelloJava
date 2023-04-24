package hellojava.concurrent.lesson12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 学习信号量
 */
public class SemaphoreDemo2 {
    private static final Logger logger = LoggerFactory.getLogger(SemaphoreDemo2.class);

    private static final Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws Throwable {
        semaphore.acquire();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                int count = 0;

                while (true) {
                    try {
                        logger.info("等待信号！");

                        semaphore.acquire();

                        count++;

                        logger.info("获得信号！\t{}", count);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        };

        thread1.start();

        Thread.sleep(2000L);

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                Random random = new Random();

                while (true) {
                    logger.info("发送信号！");

                    semaphore.release();

                    try {
                        Thread.sleep( (random.nextInt(3) + 3) * 1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        thread2.start();


        Thread.sleep(10* 1000L);
    }
}
