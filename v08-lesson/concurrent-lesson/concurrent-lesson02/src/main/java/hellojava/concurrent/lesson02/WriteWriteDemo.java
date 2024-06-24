package hellojava.concurrent.lesson02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Tykkidream on 2017/11/5.
 */
public class WriteWriteDemo {
    private static Logger logger = LoggerFactory.getLogger(WriteWriteDemo.class);

    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock_Public readWriteLock = new ReentrantReadWriteLock_Public();

        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        WriteLockBusinessThread thread1 = new WriteLockBusinessThread(writeLock);
        WriteLockBusinessThread thread2 = new WriteLockBusinessThread(writeLock);
        WriteLockBusinessThread thread3 = new WriteLockBusinessThread(writeLock);
        WriteLockBusinessThread thread4 = new WriteLockBusinessThread(writeLock);
        WriteLockBusinessThread thread5 = new WriteLockBusinessThread(writeLock);

        // 试验读读共享的情况
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReadLockBusinessThread thread6 = new ReadLockBusinessThread(readLock);

        thread1.start();
        thread2.start();

        Thread.sleep(100);

        logger.info("可能正在等待获取【读取】锁的线程：{}", readWriteLock.getQueuedReaderThreads());
        logger.info("可能正在等待获取【写入】锁的线程：{}", readWriteLock.getQueuedWriterThreads());

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
