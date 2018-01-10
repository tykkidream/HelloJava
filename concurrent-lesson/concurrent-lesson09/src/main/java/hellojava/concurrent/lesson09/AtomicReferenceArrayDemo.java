package hellojava.concurrent.lesson09;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Created by Tykkidream on 2018/1/6.
 */
public class AtomicReferenceArrayDemo {
    public static void main(String[] args) {
        String[] data = new String[] { "we", "are", "young" };

        AtomicReferenceArray<String> atomicReferenceArray = new AtomicReferenceArray(data);


        System.out.println("初始");
        System.out.println(atomicReferenceArray);


        System.out.println("\ncompareAndSet");
        atomicReferenceArray.compareAndSet(0, "we", "you");
        atomicReferenceArray.compareAndSet(0, "we", "all");
        System.out.println(atomicReferenceArray);


        System.out.println("\nlazySet");
        atomicReferenceArray.lazySet(1, "all");
        System.out.println(atomicReferenceArray);

        System.out.println("\nweakCompareAndSet");
        atomicReferenceArray.weakCompareAndSet(1,"you","me");
        System.out.println(atomicReferenceArray);
        atomicReferenceArray.weakCompareAndSet(1,"you","non");
        System.out.println(atomicReferenceArray);


        System.out.println("\ngetAndSet");
        String andSet = atomicReferenceArray.getAndSet(1, "not is");
        System.out.println(andSet);
        System.out.println(atomicReferenceArray);
    }
}
