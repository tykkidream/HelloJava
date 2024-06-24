package hellojava.concurrent.lesson09;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by Tykkidream on 2018/1/6.
 */
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        int[] data = new int[] { 1, 2 };

        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(data);


        System.out.println("初始");
        System.out.println(JSON.toJSONString(atomicIntegerArray));

        // 将位置 i 的元素以原子方式设置为给定值，并返回旧值。
        System.out.println("\ngetAndSet");
        atomicIntegerArray.getAndSet(0, 3);
        System.out.println(atomicIntegerArray.get(0));
        System.out.println(JSON.toJSONString(atomicIntegerArray));
        System.out.println(JSON.toJSONString(data));


        System.out.println("\ncompareAndSet");
        atomicIntegerArray.compareAndSet(0, 3, 4);
        atomicIntegerArray.compareAndSet(0, 3, 6);
        System.out.println(JSON.toJSONString(atomicIntegerArray));


        System.out.println("\nlazySet");
        atomicIntegerArray.lazySet(1, 10);
        System.out.println(JSON.toJSONString(atomicIntegerArray));

        System.out.println("\nweakCompareAndSet");
        atomicIntegerArray.weakCompareAndSet(1,1,100);
        System.out.println(JSON.toJSONString(atomicIntegerArray));
        atomicIntegerArray.weakCompareAndSet(1,10,100);
        System.out.println(JSON.toJSONString(atomicIntegerArray));


        System.out.println("\ngetAndSet");
        int andSet = atomicIntegerArray.getAndSet(1, 10);
        System.out.println(andSet);
        System.out.println(JSON.toJSONString(atomicIntegerArray));


        System.out.println("\ngetAndAdd");
        int andAdd = atomicIntegerArray.getAndAdd(1, 15);
        System.out.println(andAdd);
        System.out.println(JSON.toJSONString(atomicIntegerArray));
        int andAdd2 = atomicIntegerArray.getAndAdd(1, 15);
        System.out.println(andAdd2);
        System.out.println(JSON.toJSONString(atomicIntegerArray));


        System.out.println("\ngetAndDecrement");
        int andDecrement = atomicIntegerArray.getAndDecrement(0);
        System.out.println(andDecrement);
        System.out.println(JSON.toJSONString(atomicIntegerArray));
        int andDecrement2 = atomicIntegerArray.getAndDecrement(0);
        System.out.println(andDecrement2);
        System.out.println(JSON.toJSONString(atomicIntegerArray));


        System.out.println("\ngetAndIncrement");
        int andIncrement = atomicIntegerArray.getAndIncrement(0);
        System.out.println(andIncrement);
        System.out.println(JSON.toJSONString(atomicIntegerArray));
        int andIncrement2 = atomicIntegerArray.getAndIncrement(0);
        System.out.println(andIncrement2);
        System.out.println(JSON.toJSONString(atomicIntegerArray));


        System.out.println("\naddAndGet");
        int andGet = atomicIntegerArray.addAndGet(0, 11);
        System.out.println(andGet);
        System.out.println(JSON.toJSONString(atomicIntegerArray));
        int andGet2 = atomicIntegerArray.addAndGet(0, 12);
        System.out.println(andGet2);
        System.out.println(JSON.toJSONString(atomicIntegerArray));


        System.out.println("\nincrementAndGet");
        int incrementAndGet = atomicIntegerArray.incrementAndGet(0);
        System.out.println(incrementAndGet);
        System.out.println(JSON.toJSONString(atomicIntegerArray));
        int incrementAndGet2 = atomicIntegerArray.incrementAndGet(0);
        System.out.println(incrementAndGet2);
        System.out.println(JSON.toJSONString(atomicIntegerArray));


        System.out.println("\ndecrementAndGet");
        int decrementAndGet = atomicIntegerArray.decrementAndGet(0);
        System.out.println(decrementAndGet);
        System.out.println(JSON.toJSONString(atomicIntegerArray));
        int decrementAndGet2 = atomicIntegerArray.decrementAndGet(0);
        System.out.println(decrementAndGet2);
        System.out.println(JSON.toJSONString(atomicIntegerArray));

    }
}
