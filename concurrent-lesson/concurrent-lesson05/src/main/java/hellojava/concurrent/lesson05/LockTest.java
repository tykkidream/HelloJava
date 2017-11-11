package hellojava.concurrent.lesson05;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tykkidream on 2017/11/10.
 */
public class LockTest extends TestCaseTemplate implements TestCase {
    ReentrantLock lock = new ReentrantLock();

    private long countValue;
    private int index;


    public LockTest(int round) {
        super(round);
    }

    /**
     * synchronized 关键字不在方法签名里面，所以不涉及重载问题
     */
    public long getValue() {
        try {
            lock.lock();
            return countValue;
        } finally {
            lock.unlock();
        }
    }

    public void sumValue() {
        for (int i = 0; i < round; i++) {
            try {
                lock.lock();
                countValue += preInit[index++ % round];
            } finally {
                lock.unlock();
            }
        }
    }
}
