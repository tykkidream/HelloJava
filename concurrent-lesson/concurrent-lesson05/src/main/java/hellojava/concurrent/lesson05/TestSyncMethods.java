package hellojava.concurrent.lesson05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by Tykkidream on 2017/11/10.
 */
public class TestSyncMethods {
    private static Logger logger = LoggerFactory.getLogger(TestTemplate.class);

    public static void test(int round,int threadNum,CyclicBarrier cyclicBarrier){
        new SyncTest("Sync",round,threadNum,cyclicBarrier).testTime();
        new LockTest("Lock",round,threadNum,cyclicBarrier).testTime();
        new AtomicTest("Atom",round,threadNum,cyclicBarrier).testTime();
    }

    public static void main(String args[]){

        for(int i=0;i<5;i++){
            int round=100000*(i+1);
            int threadNum=5*(i+1);
            CyclicBarrier cb=new CyclicBarrier(threadNum*2+1);

            logger.info("==========================");
            logger.info("round: {} thread : {}", round, threadNum);
            test(round,threadNum,cb);
        }
    }
}
