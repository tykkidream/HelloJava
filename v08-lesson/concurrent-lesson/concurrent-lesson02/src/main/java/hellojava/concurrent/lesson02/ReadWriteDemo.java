package hellojava.concurrent.lesson02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Tykkidream on 2017/11/5.
 */
public class ReadWriteDemo {
    private static Logger logger = LoggerFactory.getLogger(ReadReadDemo.class);

    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock_Public readWriteLock = new ReentrantReadWriteLock_Public();

        // 试验读读共享的情况
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

        ReadLockBusinessThread thread1 = new ReadLockBusinessThread(readLock);
        ReadLockBusinessThread thread2 = new ReadLockBusinessThread(readLock);
        ReadLockBusinessThread thread3 = new ReadLockBusinessThread(readLock);
        ReadLockBusinessThread thread4 = new ReadLockBusinessThread(readLock);
        ReadLockBusinessThread thread5 = new ReadLockBusinessThread(readLock);


        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        WriteLockBusinessThread thread6 = new WriteLockBusinessThread(writeLock);

        thread1.start();
        thread2.start();

        thread6.start();

        Thread.sleep(100);

        logger.info("可能正在等待获取【读取】锁的线程：{}", readWriteLock.getQueuedReaderThreads());
        logger.info("可能正在等待获取【写入】锁的线程：{}", readWriteLock.getQueuedWriterThreads());

        thread3.start();
        thread4.start();
        thread5.start();

        Thread.sleep(100);

        logger.info("可能正在等待获取【读取】锁的线程：{}", readWriteLock.getQueuedReaderThreads());
        logger.info("可能正在等待获取【写入】锁的线程：{}", readWriteLock.getQueuedWriterThreads());
    }
}
