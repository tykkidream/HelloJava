package hellojava.concurrent.lesson08;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Tykkidream on 2017/11/12.
 */
public abstract class Accumulator {
    public static long cycles = 50000L;
    // Number of modifiers and readers during each test
    private static final int N = 4;
    /**
     * 同时启动的Modifier和Reader的个数N=4 表示同时启动4个Modifier和4个Reader
     *
     * 每个Modifier和Reader 在run中循环cycles次执行accumulate()和read()
     */
    public static ExecutorService exec = Executors.newFixedThreadPool(2 * N);
    private static CyclicBarrier barrier = new CyclicBarrier(2 * N + 1);
    protected volatile int index = 0;
    protected volatile long value = 0;
    protected long duration = 0;
    protected String id = "";
    // A big int array
    protected static final int SIZE = 100000;
    protected static int[] preLoad = new int[SIZE];

    static {
        // Load the array of random numbers:
        Random random = new Random(47);
        for (int i = 0; i < SIZE; i++) {
            preLoad[i] = random.nextInt();
        }
    }

    public abstract void accumulate();

    public abstract long read();

    // 在上面这两个方法中 分别使用不同的同步机制,保证同一时刻,只有一个线程可以调用这两个方法当中的一个
    // BaseLine extends Accumulator 中这两个方法的实现没有使用任何同步机制
    // 导致两个方法不是线程安全的,但是是性能最好的,从而作为一个基准
    //

    private class Modifier implements Runnable {
        public void run() {
            for (int i = 0; i < cycles; i++) {
                accumulate();
            }
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class Reader implements Runnable {
        private volatile long value;

        public void run() {
            for (int i = 0; i < cycles; i++) {
                value = read();
            }
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void timedTest() {
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            exec.execute(new Modifier());//4 Modifiers
            exec.execute(new Reader());//4 Readers
            // N个Modifier() 和 N个Reader() 分别调用了1次的run进行一次循环的accumulate() 和 read()(5000L次)
            // 然后调用barrier.await(); 挂起
            // accumulate() 和 read() 这两个抽象方法放到子类中实现,
            // 有的子类不同步,有的使用synchronized同步,有的使用Lock同步,有的使用Atomic对象
            // 这样即可测试使用不同同步方法的性能(有耗时时间表示)
        }
        try {
            barrier.await();
            // 确保每个Modifier和Reader 中的run方法也必须执行完毕
            // 同时 假如Modifier和Reader中有先执行到barrier.await();挂起的线程继续执行,然后从run()方法中返回
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        duration = System.nanoTime() - start;
        System.out.println(String.format("%-13s: %13d", id, duration));
    }

    public static void report(Accumulator a1, Accumulator a2) {
        System.out.println(String.format("%-22s: %.2f", a1.id +
                "/" + a2.id, a1.duration / (double) a2.duration));
    }

}
