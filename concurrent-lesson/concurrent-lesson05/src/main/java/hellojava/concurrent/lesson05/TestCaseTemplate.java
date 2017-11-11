package hellojava.concurrent.lesson05;

import java.util.Random;

/**
 * Created by Tykkidream on 2017/11/11.
 */
public abstract class TestCaseTemplate implements TestCase {
    protected int round;
    protected int[] preInit;

    Random random = new Random(47);


    public TestCaseTemplate(int round) {
        this.round = round;
        this.preInit = new int[round];
        for (int i = 0; i < round; i++) {
            preInit[i] = random.nextInt(100);
        }
    }
}
