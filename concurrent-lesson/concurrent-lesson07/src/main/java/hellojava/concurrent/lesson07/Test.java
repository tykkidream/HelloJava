package hellojava.concurrent.lesson07;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tykkidream on 2017/11/11.
 */
public class Test {

    private static final int NUM_THREADS = 4;

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        test.test();
    }

    private void test() throws InterruptedException {
        Lock lock = new ReentrantLock();
        LockVsSync lockVsSync = new LockVsSync();

        for (int t = 0; t < NUM_THREADS; t++) {
            if (t == 0) {
                // Set the first thread as the master which will be measured
                // The other threads are only to cause contention
                Runner r = new Runner(lock, true, lockVsSync);
                r.start();
            } else {
                Runner r = new Runner(lock, false, lockVsSync);
                r.start();
            }
        }

        synchronized (this) {
            // Hold the main thread from completing
            wait();
        }
    }
}
