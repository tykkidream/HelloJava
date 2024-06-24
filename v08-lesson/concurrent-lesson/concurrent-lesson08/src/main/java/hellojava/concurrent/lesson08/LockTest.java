package hellojava.concurrent.lesson08;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tykkidream on 2017/11/12.
 */
public class LockTest extends Accumulator {
    {
        id = "Lock";
    }

    private Lock lock = new ReentrantLock();

    public void accumulate() {
        lock.lock();
        try {
            value += preLoad[index++];
            if (index >= SIZE - 5) index = 0;
        } finally {
            lock.unlock();
        }
    }

    public long read() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }
}

