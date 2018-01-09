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



    }
}
