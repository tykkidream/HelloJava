package hellojava.concurrent.lesson05;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Tykkidream on 2017/11/10.
 */
public class AtomicTest extends TestCaseTemplate {
    protected AtomicLong countValueAtmoic = new AtomicLong(0);

    protected AtomicInteger indexAtomic = new AtomicInteger(0);

    public AtomicTest(int round) {
        super(round);
    }

    /**
     * synchronized关键字不在方法签名里面，所以不涉及重载问题
     */
    public long getValue() {
        return countValueAtmoic.get();
    }

    public void sumValue() {
        for (int i = 0; i < round; i++) {
            sumValueOfLock();
        }
    }

    private void sumValueOfLock() {
        countValueAtmoic.addAndGet(super.preInit[indexAtomic.get() % round]);
    }
}
