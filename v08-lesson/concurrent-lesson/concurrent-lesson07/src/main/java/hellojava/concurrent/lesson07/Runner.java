package hellojava.concurrent.lesson07;

import net.openhft.chronicle.core.util.Histogram;

import java.util.concurrent.locks.Lock;

/**
 * Created by Tykkidream on 2017/11/11.
 */
public class Runner extends Thread {
    private static final boolean COORDINATED_OMISSION = false;
    //Either run testing Lock or testing synchronized
    private static final boolean IS_LOCK = false;

    private Lock lock;
    private boolean master;
    private LockVsSync lockVsSync;

    public Runner(Lock lock, boolean master, LockVsSync lockVsSync) {
        this.lock = lock;
        this.master = master;
        this.lockVsSync = lockVsSync;
    }

    @Override
    public void run() {
        Histogram histogram = null;
        if (master)
            histogram = new Histogram();

        long rate = 1000; // expect 1 every microsecond
        long now = 0;
        for (int i = -10000; i < 200_000_000; i++) {
            if (i == 0) {
                now = System.nanoTime();
            } else if (i > 0) {
                if (!COORDINATED_OMISSION) {
                    now += rate;
                    while (System.nanoTime() < now)
                        ;
                } else
                    now = System.nanoTime();
            }
            if (IS_LOCK)
                lockVsSync.testLock(lock);
            else
                lockVsSync.testSync();

            if (i >= 0 && master) {
                histogram.sample(System.nanoTime() - now);
            }
        }
        if (master) {
            System.out.println(histogram.toMicrosFormat());
            System.exit(0);
        }
    }
}
