package hellojava.concurrent.lesson07;

import java.util.concurrent.locks.Lock;

/**
 * Created by Tykkidream on 2017/11/11.
 */
public class LockVsSync {
    public void testLock(Lock rlock) {
        rlock.lock();
        try {
            for (int i = 0; i < 2; i++) {
                double x = 10 / 4.5 + i;
            }
        } finally {
            rlock.unlock();
        }
    }

    public synchronized void testSync() {
        for (int i = 0; i < 2; i++) {
            double x = 10 / 4.5 + i;
        }
    }
}
