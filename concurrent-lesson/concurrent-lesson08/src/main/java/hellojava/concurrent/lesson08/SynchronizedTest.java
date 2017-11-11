package hellojava.concurrent.lesson08;

/**
 * Created by Tykkidream on 2017/11/12.
 */
public class SynchronizedTest extends Accumulator {
    {
        id = "Synchronized";
    }

    public synchronized void accumulate() {
        value += preLoad[index++];
        if (index >= SIZE - 5) index = 0;
    }

    public synchronized long read() {
        return value;
    }
}