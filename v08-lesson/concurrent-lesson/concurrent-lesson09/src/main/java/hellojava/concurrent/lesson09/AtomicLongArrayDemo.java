package hellojava.concurrent.lesson09;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.atomic.AtomicLongArray;

/**
 * Created by Tykkidream on 2018/1/6.
 */
public class AtomicLongArrayDemo {
    public static void main(String[] args) {
        long[] data = new long[] { 1L, 2L };

        AtomicLongArray atomicLongArray = new AtomicLongArray(data);


        System.out.println("初始");
        System.out.println(JSON.toJSONString(atomicLongArray));

        // 将位置 i 的元素以原子方式设置为给定值，并返回旧值。
        System.out.println("\ngetAndSet");
        atomicLongArray.getAndSet(0, 3L);
        System.out.println(atomicLongArray.get(0));
        System.out.println(JSON.toJSONString(atomicLongArray));
        System.out.println(JSON.toJSONString(data));


        System.out.println("\ncompareAndSet");
        atomicLongArray.compareAndSet(0, 3L, 4L);
        atomicLongArray.compareAndSet(0, 3L, 6L);
        System.out.println(JSON.toJSONString(atomicLongArray));


        System.out.println("\nlazySet");
        atomicLongArray.lazySet(1, 10L);
        System.out.println(JSON.toJSONString(atomicLongArray));

        System.out.println("\nweakCompareAndSet");
        atomicLongArray.weakCompareAndSet(1,1L,100L);
        System.out.println(JSON.toJSONString(atomicLongArray));
        atomicLongArray.weakCompareAndSet(1,10L,100L);
        System.out.println(JSON.toJSONString(atomicLongArray));


        System.out.println("\ngetAndSet");
        long andSet = atomicLongArray.getAndSet(1, 10L);
        System.out.println(andSet);
        System.out.println(JSON.toJSONString(atomicLongArray));


        System.out.println("\ngetAndAdd");
        long andAdd = atomicLongArray.getAndAdd(1, 15L);
        System.out.println(andAdd);
        System.out.println(JSON.toJSONString(atomicLongArray));
        long andAdd2 = atomicLongArray.getAndAdd(1, 15L);
        System.out.println(andAdd2);
        System.out.println(JSON.toJSONString(atomicLongArray));


        System.out.println("\ngetAndDecrement");
        long andDecrement = atomicLongArray.getAndDecrement(0);
        System.out.println(andDecrement);
        System.out.println(JSON.toJSONString(atomicLongArray));
        long andDecrement2 = atomicLongArray.getAndDecrement(0);
        System.out.println(andDecrement2);
        System.out.println(JSON.toJSONString(atomicLongArray));


        System.out.println("\ngetAndIncrement");
        long andIncrement = atomicLongArray.getAndIncrement(0);
        System.out.println(andIncrement);
        System.out.println(JSON.toJSONString(atomicLongArray));
        long andIncrement2 = atomicLongArray.getAndIncrement(0);
        System.out.println(andIncrement2);
        System.out.println(JSON.toJSONString(atomicLongArray));


        System.out.println("\naddAndGet");
        long andGet = atomicLongArray.addAndGet(0, 11L);
        System.out.println(andGet);
        System.out.println(JSON.toJSONString(atomicLongArray));
        long andGet2 = atomicLongArray.addAndGet(0, 12L);
        System.out.println(andGet2);
        System.out.println(JSON.toJSONString(atomicLongArray));


        System.out.println("\nincrementAndGet");
        long incrementAndGet = atomicLongArray.incrementAndGet(0);
        System.out.println(incrementAndGet);
        System.out.println(JSON.toJSONString(atomicLongArray));
        long incrementAndGet2 = atomicLongArray.incrementAndGet(0);
        System.out.println(incrementAndGet2);
        System.out.println(JSON.toJSONString(atomicLongArray));


        System.out.println("\ndecrementAndGet");
        long decrementAndGet = atomicLongArray.decrementAndGet(0);
        System.out.println(decrementAndGet);
        System.out.println(JSON.toJSONString(atomicLongArray));
        long decrementAndGet2 = atomicLongArray.decrementAndGet(0);
        System.out.println(decrementAndGet2);
        System.out.println(JSON.toJSONString(atomicLongArray));
    }
}
