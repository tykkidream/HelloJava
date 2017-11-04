package hellojava.concurrent.lesson01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Tykkidream on 2017/10/29.
 */
public class SyncBusinessThread extends Thread {
    private static Logger logger = LoggerFactory.getLogger(SyncBusinessThread.class);

    protected CountDownLatch latch;
    protected SyncBusinessEntity entity;
    protected int key;
    protected int maxValue;

    public SyncBusinessThread(CountDownLatch latch, SyncBusinessEntity entity, int key, int maxValue) {
        this.latch = latch;
        this.entity = entity;
        this.key = key;
        this.maxValue = maxValue;
    }
}
