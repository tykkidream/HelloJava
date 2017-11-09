package hellojava.concurrent.lesson05;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by Tykkidream on 2017/11/10.
 */
public class SyncTest extends TestTemplate {
    public SyncTest(String _id, int _round, int _threadNum, CyclicBarrier _cb) {
        super(_id, _round, _threadNum, _cb);
    }

    synchronized void sumValue() {
        super.countValue += preInit[index++ % round];
    }

    long getValue() {
        return super.countValue;
    }
}
