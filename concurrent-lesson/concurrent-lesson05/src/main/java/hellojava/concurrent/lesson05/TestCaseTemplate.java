package hellojava.concurrent.lesson05;

import java.util.Random;

/**
 * Created by Tykkidream on 2017/11/11.
 */
public abstract class TestCaseTemplate implements TestCase {
    protected int round;
    protected int[] preInit;

    public TestCaseTemplate(int round) {
        this.round = round;
        this.preInit = new int[round];

        Random random = new Random(47);
        for (int i = 0; i < round; i++) {
            preInit[i] = random.nextInt(100);
        }
    }
}
