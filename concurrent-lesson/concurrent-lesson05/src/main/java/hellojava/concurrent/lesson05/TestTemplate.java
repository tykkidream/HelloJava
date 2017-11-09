package hellojava.concurrent.lesson05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Tykkidream on 2017/11/9.
 */
public abstract class TestTemplate {
    private static Logger logger = LoggerFactory.getLogger(TestTemplate.class);

    private String id;
    protected int round;
    private int threadNum;
    protected long countValue;
    protected AtomicLong countValueAtmoic = new AtomicLong(0);
    protected int[] preInit;
    protected int index;
    protected AtomicInteger indexAtomic = new AtomicInteger(0);
    Random r = new Random(47);
    //任务栅栏，同批任务，先到达wait的任务挂起，一直等到全部任务到达制定的wait地点后，才能全部唤醒，继续执行
    private CyclicBarrier cb;

    public TestTemplate(String _id, int _round, int _threadNum, CyclicBarrier _cb) {
        this.id = _id;
        this.round = _round;
        this.threadNum = _threadNum;
        cb = _cb;
        preInit = new int[round];
        for (int i = 0; i < preInit.length; i++) {
            preInit[i] = r.nextInt(100);
        }
    }

    abstract void sumValue();

    /*
     * 对long的操作是非原子的，原子操作只针对32位
     * long是64位，底层操作的时候分2个32位读写，因此不是线程安全
     */
    abstract long getValue();

    public void testTime() {
        ExecutorService se = Executors.newCachedThreadPool();
        long start = System.nanoTime();
        //同时开启2*ThreadNum个数的读写线程
        for (int i = 0; i < threadNum; i++) {
            se.execute(new Runnable() {
                public void run() {
                    for (int i = 0; i < round; i++) {
                        sumValue();
                    }

                    //每个线程执行完同步方法后就等待
                    try {
                        cb.await();
                    } catch (Throwable throwable) {
                        logger.error(throwable.getMessage(), throwable);
                    }
                }
            });
            se.execute(new Runnable() {
                public void run() {

                    getValue();
                    try {
                        //每个线程执行完同步方法后就等待
                        cb.await();
                    } catch (Throwable throwable) {
                        logger.error(throwable.getMessage(), throwable);
                    }
                }
            });
        }

        try {
            //当前统计线程也wait,所以CyclicBarrier的初始值是threadNum*2+1
            cb.await();
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
        }
        //所有线程执行完成之后，才会跑到这一步
        long duration = System.nanoTime() - start;

        logger.info("{} = {}", id, duration);
    }
}
