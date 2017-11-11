package hellojava.concurrent.lesson06;

/**
 * Created by Tykkidream on 2017/11/11.
 */
public class SimpleMicroBenchmark {
    public static long test(Incrementable inc) {
        long start = System.nanoTime();
        for (long i = 0; i < 100000000; i++) {
            inc.increment();
        }
        return System.nanoTime() - start;
    }
    public static void main(String[] args) {
        long syncTime = test(new SynchronizingTest());
        long lockTime = test(new LockingTest());
        System.out.println(String.format("Sync: %1$10d", syncTime));
        System.out.println(String.format("Lock: %1$10d", lockTime));
        System.out.println(String.format("Lock/Synchronized: %1$.3f", lockTime/(double)syncTime));
    }
}
