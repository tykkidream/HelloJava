package hellojava.concurrent.lesson01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Tykkidream on 2017/10/29.
 */
public class NoLockDemo {
    private static Logger logger = LoggerFactory.getLogger(NoLockDemo.class);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        SyncBusinessEntity entity = new SyncBusinessEntity();

        SyncBusinessThread thread1 = new SyncBusinessThread(null ,latch, entity, 1, 5);
        SyncBusinessThread thread2 = new SyncBusinessThread(null, latch, entity, 2, 5);

        thread1.start();
        thread2.start();

        latch.await();

        // 多次测试，测出了一个感觉严重的错误结果，应该总共10个元素，少了一个，具体看项目根目录下/doc/NoLockDemo.PNG文件。
        logger.info("最终结果 共 {} 个元素：{}", entity.size(), entity.toString());
    }

}
