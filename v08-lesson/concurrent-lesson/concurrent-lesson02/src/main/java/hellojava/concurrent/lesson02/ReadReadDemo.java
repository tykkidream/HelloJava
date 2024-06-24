package hellojava.concurrent.lesson02;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Tykkidream on 2017/11/5.
 */
public class ReadReadDemo {

    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        // 试验读读共享的情况
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

        ReadLockBusinessThread thread1 = new ReadLockBusinessThread(readLock);
        ReadLockBusinessThread thread2 = new ReadLockBusinessThread(readLock);
        ReadLockBusinessThread thread3 = new ReadLockBusinessThread(readLock);
        ReadLockBusinessThread thread4 = new ReadLockBusinessThread(readLock);
        ReadLockBusinessThread thread5 = new ReadLockBusinessThread(readLock);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
