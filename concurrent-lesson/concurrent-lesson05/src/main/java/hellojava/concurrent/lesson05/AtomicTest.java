package hellojava.concurrent.lesson05;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by Tykkidream on 2017/11/10.
 */
public class AtomicTest extends TestTemplate {
    public AtomicTest(String _id, int _round, int _threadNum, CyclicBarrier _cb) {
        super(_id, _round, _threadNum, _cb);
    }

    /**
     * synchronized关键字不在方法签名里面，所以不涉及重载问题
     */
    long getValue() {
        return super.countValueAtmoic.get();
    }

    void sumValue() {
        super.countValueAtmoic.addAndGet(super.preInit[indexAtomic.get() % round]);
    }
}
