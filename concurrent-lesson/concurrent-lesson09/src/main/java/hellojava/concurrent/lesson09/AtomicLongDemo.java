package hellojava.concurrent.lesson09;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Tykkidream on 2018/1/1.
 */
public class AtomicLongDemo {
    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong();

        /*
        AtomicLong 的使用方法与 AtomicInteger 一模一样，因为方法都一样。
         */

        System.out.println("初始");
        System.out.println(atomicLong.get());

        // 以原子方式设置为newValue的值，并返回旧值，
        System.out.println("\ngetAndIncrement");
        System.out.println(atomicLong.getAndIncrement());
        System.out.println(atomicLong.get());

        // 以原子方式将输入的数值与实例中的值（AtomicInteger里的value）相加，并返回结果，
        System.out.println("\naddAndGet");
        System.out.println(atomicLong.addAndGet(10));

        // 如果输入的数值等于预期值，则以原子方式将该值设置为输入的值，
        System.out.println("\ncompareAndSet");
        System.out.println(atomicLong.compareAndSet(10, 100));
        System.out.println(atomicLong.compareAndSet(11, 1000));
        System.out.println(atomicLong.get());

        // 最终会设置成newValue，使用lazySet设置值后，可能导致其他线程在之后的一小段时间内还是可以读到旧的值\。
        System.out.println("\nlazySet");
        atomicLong.lazySet(666);
        System.out.println(atomicLong.get());
    }
}
