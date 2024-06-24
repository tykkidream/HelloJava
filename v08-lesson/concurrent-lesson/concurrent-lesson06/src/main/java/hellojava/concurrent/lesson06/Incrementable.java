package hellojava.concurrent.lesson06;

/**
 * Created by Tykkidream on 2017/11/11.
 */
public abstract class Incrementable {
    protected long counter = 0;
    public abstract void increment();
}
