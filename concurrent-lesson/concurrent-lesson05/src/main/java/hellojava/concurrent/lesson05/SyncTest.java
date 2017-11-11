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

    public synchronized void sumValue() {
        countValue += preInit[index++ % round];
    }

    public long getValue() {
        return countValue;
    }
}
