package hellojava.concurrent.lesson05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Tykkidream on 2017/11/10.
 */
public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void test(int testThreadNum, int round) {
        new TestProject("Sync", testThreadNum, new SyncTest(round)).testTime();
        new TestProject("Lock", testThreadNum, new LockTest(round)).testTime();
        new TestProject("Atom", testThreadNum, new AtomicTest(round)).testTime();
    }

    public static void main(String args[]) {

        for (int i = 0; i < 5; i++) {
            int round = 100000 * (i + 1);
            int threadNum = 5 * (i + 1);

            logger.info("==========================");
            logger.info("testThreadNum : {} round: {}", threadNum, round);
            test(threadNum, round);
        }
    }
}
