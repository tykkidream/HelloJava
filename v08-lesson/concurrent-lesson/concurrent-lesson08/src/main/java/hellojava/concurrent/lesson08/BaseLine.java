package hellojava.concurrent.lesson08;

/**
 * Created by Tykkidream on 2017/11/12.
 */
public class BaseLine extends Accumulator {
    {
        id = "BaseLine";
    }

    public void accumulate() {
        value += preLoad[index++];
        if (index >= SIZE - 5) index = 0;
    }

    public long read() {
        return value;
    }
}