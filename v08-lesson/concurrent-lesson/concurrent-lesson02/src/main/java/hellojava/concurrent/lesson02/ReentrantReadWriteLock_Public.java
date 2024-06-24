package hellojava.concurrent.lesson02;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Tykkidream on 2017/11/7.
 */
public class ReentrantReadWriteLock_Public extends ReentrantReadWriteLock {
    @Override
    public Collection<Thread> getQueuedThreads() {
        return super.getQueuedThreads();
    }

    @Override
    protected Collection<Thread> getQueuedReaderThreads() {
        return super.getQueuedReaderThreads();
    }

    @Override
    protected Collection<Thread> getQueuedWriterThreads() {
        return super.getQueuedWriterThreads();
    }
}