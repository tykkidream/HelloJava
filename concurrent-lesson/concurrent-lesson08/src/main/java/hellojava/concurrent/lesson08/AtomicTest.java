package hellojava.concurrent.lesson08;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Tykkidream on 2017/11/12.
 */
public class AtomicTest extends Accumulator {
    {
        id = "Atomic";
    }

    private AtomicInteger index = new AtomicInteger(0);
    private AtomicLong value = new AtomicLong(0);

    public void accumulate() {
        //Get value before increment.
        //不同步的代码 是这样的value += preLoaded[index++];
        //这里同时依赖value和index两个Atomic对象
        //这样使用Atomic对象也不能保证程序正常执行了
        int i = index.getAndIncrement();
        //Get value before add.
        value.getAndAdd(preLoad[i]);
        if (++i >= SIZE - 5) index.set(0);
    }

    public long read() {
        return value.get();
    }
}
