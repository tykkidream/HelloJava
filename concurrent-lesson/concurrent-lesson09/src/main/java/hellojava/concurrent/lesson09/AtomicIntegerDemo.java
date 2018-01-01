package hellojava.concurrent.lesson09;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Tykkidream on 2018/1/1.
 */
public class AtomicIntegerDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();

        System.out.println("初始");
        System.out.println(atomicInteger.get());

        // 以原子方式设置为newValue的值，并返回旧值，
        System.out.println("\ngetAndIncrement");
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());

        // 以原子方式将输入的数值与实例中的值（AtomicInteger里的value）相加，并返回结果，
        System.out.println("\naddAndGet");
        System.out.println(atomicInteger.addAndGet(10));

        // 如果输入的数值等于预期值，则以原子方式将该值设置为输入的值，
        System.out.println("\ncompareAndSet");
        System.out.println(atomicInteger.compareAndSet(10, 100));
        System.out.println(atomicInteger.compareAndSet(11, 1000));
        System.out.println(atomicInteger.get());

        // 最终会设置成newValue，使用lazySet设置值后，可能导致其他线程在之后的一小段时间内还是可以读到旧的值\。
        System.out.println("\nlazySet");
        atomicInteger.lazySet(666);
        System.out.println(atomicInteger.get());
    }
}
