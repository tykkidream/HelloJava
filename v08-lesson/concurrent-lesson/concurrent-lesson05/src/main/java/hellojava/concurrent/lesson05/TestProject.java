package hellojava.concurrent.lesson05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Tykkidream on 2017/11/11.
 */
public class TestProject {
    private static Logger logger = LoggerFactory.getLogger(TestProject.class);

    private String testName;
    private int testThreadNum;

    private TestCase testCase;


    /**
     * 任务栅栏，同批任务，先到达wait的任务挂起，一直等到全部任务到达制定的wait地点后，才能全部唤醒，继续执行
     */
    private CyclicBarrier cyclicBarrier;

    /**
     *
     * @param testName 测试名称
     * @param testThreadNum 测试时的线程数
     * @param testCase
     */
    public TestProject(String testName, int testThreadNum, TestCase testCase) {
        this.testName = testName;
        this.testThreadNum = testThreadNum;
        this.testCase = testCase;
        this.cyclicBarrier = new CyclicBarrier(testThreadNum * 2 + 1);
    }

    public void testTime() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 获取当前开始时的纳秒时间
        long start = System.nanoTime();

        // 同时开启2*ThreadNum个数的读写线程
        for (int i = 0; i < testThreadNum; i++) {
            executorService.execute(new TestCase1());
            executorService.execute(new TestCase2());
        }

        try {
            // 当前统计线程也wait,所以CyclicBarrier的初始值是threadNum*2+1
            cyclicBarrier.await();
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
        }

        // 所有线程执行完成之后，才会跑到这一步
        // 计算花费的纳秒级的时间量
        long duration = System.nanoTime() - start;

        logger.info("{} = {}", testName, duration);

        executorService.shutdownNow();
    }

    private class TestCase1 implements Runnable {
        public void run() {
            testCase.sumValue();
            // 每个线程执行完同步方法后就等待
            try {
                cyclicBarrier.await();
            } catch (Throwable throwable) {
                logger.error(throwable.getMessage(), throwable);
            }
        }
    }

    private class TestCase2 implements Runnable {
        public void run() {
            testCase.getValue();
            try {
                // 每个线程执行完同步方法后就等待
                cyclicBarrier.await();
            } catch (Throwable throwable) {
                logger.error(throwable.getMessage(), throwable);
            }
        }
    }
}
