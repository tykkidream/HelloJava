package hellojava.concurrent.lesson05;

/**
 * Created by Tykkidream on 2017/11/10.
 */
public class SyncTest extends TestCaseTemplate {
    private long countValue;
    private int index;

    public SyncTest(int round) {
        super(round);
    }

    public long getValue() {
        return countValue;
    }

    public void sumValue() {
        for (int i = 0; i < round; i++) {
            sumValueOfLock();
        }
    }

    private synchronized void sumValueOfLock() {
        countValue += preInit[index++ % round];
    }

}
