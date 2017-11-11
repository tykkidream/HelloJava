package hellojava.concurrent.lesson06;

/**
 * Created by Tykkidream on 2017/11/11.
 */
public class SynchronizingTest extends Incrementable {
    public synchronized void increment() {
        ++counter;
    }
}
