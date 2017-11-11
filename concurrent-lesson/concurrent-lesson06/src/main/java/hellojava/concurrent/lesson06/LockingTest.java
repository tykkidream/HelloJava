package hellojava.concurrent.lesson06;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tykkidream on 2017/11/11.
 */
public class LockingTest extends Incrementable {
    private Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            ++counter;
        } finally {
            lock.unlock();
        }
    }
}
