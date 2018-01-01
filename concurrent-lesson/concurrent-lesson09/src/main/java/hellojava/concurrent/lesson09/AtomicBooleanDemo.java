package hellojava.concurrent.lesson09;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Tykkidream on 2018/1/1.
 */
public class AtomicBooleanDemo {
    public static void main(String[] args) {
        AtomicBoolean atomicBoolean = new AtomicBoolean();

        System.out.println("初始");
        System.out.println(atomicBoolean.get());

        // 以原子方式将输入的数值与实例中的值（AtomicInteger里的value）相加，并返回结果，
        System.out.println("\ngetAndSet");
        System.out.println(atomicBoolean.getAndSet(true));
        System.out.println(atomicBoolean.get());

        // 如果输入的数值等于预期值，则以原子方式将该值设置为输入的值，
        System.out.println("\ncompareAndSet");
        System.out.println(atomicBoolean.compareAndSet(false, true));
        System.out.println(atomicBoolean.compareAndSet(true, false));
        System.out.println(atomicBoolean.get());

        // 最终会设置成newValue，使用lazySet设置值后，可能导致其他线程在之后的一小段时间内还是可以读到旧的值\。
        System.out.println("\nlazySet");
        atomicBoolean.lazySet(true);
        System.out.println(atomicBoolean.get());
    }
}
