package hellojava.concurrent.lesson03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo5 {
    private static Logger logger = LoggerFactory.getLogger(LockSupportDemo5.class);

    public static void main(String[] args) throws InterruptedException {

        final String blocker = "阻塞线程时，存在某个数据，允许外部监控工具或其它线程查看这个数据，可分析问题或其它业务。";

        Thread thread = new Thread() {
            @Override
            public void run() {
                LockSupport.park(blocker);
            }
        };

        thread.start();

        Thread.sleep(1000);

        Object getblocker = LockSupport.getBlocker(thread);

        logger.info("{}", getblocker);

        LockSupport.unpark(thread);

        Thread.sleep(1000);

        getblocker = LockSupport.getBlocker(thread);

        logger.info("{}", getblocker);
    }
}
