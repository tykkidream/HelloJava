package hellojava.concurrent.lesson08;

/**
 * Created by Tykkidream on 2017/11/12.
 */
public class SynchronizationComparisons {
    static BaseLine baseLine = new BaseLine();
    static SynchronizedTest synchronizedTest = new SynchronizedTest();
    static LockTest lockTest = new LockTest();
    static AtomicTest atomicTest = new AtomicTest();

    static void test() {
        System.out.println("============================");
        System.out.printf("%-12s : %13d\n", "Cycles", Accumulator.cycles);
        baseLine.timedTest();
        synchronizedTest.timedTest();
        lockTest.timedTest();
        atomicTest.timedTest();

        Accumulator.report(synchronizedTest, baseLine);
        Accumulator.report(lockTest, baseLine);
        Accumulator.report(atomicTest, baseLine);
        Accumulator.report(synchronizedTest, lockTest);
        Accumulator.report(synchronizedTest, atomicTest);
        Accumulator.report(lockTest, atomicTest);
    }

    public static void main(String[] args) {
        int iterations = 5;//Default execute time
        if (args.length > 0) {//Optionally change iterations
            iterations = Integer.parseInt(args[0]);
        }
        //The first time fills the thread pool
        System.out.println("Warmup");
        baseLine.timedTest();
        //这个用于初始化类中的线程池,使用Executors.newFixedThreadPool(N * 2);创建线程池和线程,这里就把线程池和线程创建好了.
        //这样后面的测试时间不收创建线程的时间影响.
        //同时也将preLoaded[]数组填满
        //Now the initial test does not include the cost
        //of starting the threads for the first time.
        for (int i = 0; i < iterations; i++) {
            test();
            //Double cycle times.
            Accumulator.cycles *= 2;
        }
        Accumulator.exec.shutdown();
    }

}
