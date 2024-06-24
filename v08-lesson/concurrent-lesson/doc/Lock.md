Lock 对比 synchronized
=====================

`synchronized` 是 java 关键字，由JVM底层实现了锁功能。

`synchronized` 修饰于方法和代码块，同一时间，方法或代码在只能被一个线程获取锁并执行其代码，而其他线程会被阻塞等待。一直等到刚才线程执行代码自动释放了锁，再从其它线程随机出一个会抢到锁。

`synchronized` 特点：

抢锁：

- 一个线程抢成功，其它线程阻塞且不可中断或失败。
- 支持重入。
- 抢一个释放的锁时，会随机一个线程抢到锁。

释放：

- `synchronized`的代码执行结束时自动释放；
- `synchronized`的代码发生异常时自动释放。

`Lock`特点：

抢锁：

- 一个线程抢成功，其它线程阻塞且不可中断或失败；
- 一个线程抢成功，其它线程阻塞，阻塞线程可被中断；
- 一个线程抢成功，其它线程直接失败；
- 一个线程抢成功，其它线程阻塞一些时间，超过时间后失败。
- 支持重入。
- 抢一个释放的锁时，支持随机一个线程抢到锁，也支持按顺序抢到锁。

释放：手动释放。

其它：支持锁使用情况的统计，比较重入次数，当前线程是否获得了锁，等待锁的线程有哪些。

总结 `Lock` 和 `synchronized` 的优缺点
 ------------------------------

首先，抢到锁的线程如果被IO或sleep等阻塞了，没有抢到锁的线程会被一直阻塞可能会等很长时间，这是比较没效率的问题。所以需要一种机制不让等待的线程一起等下去，这就要用 `Lock` 了。

其次，对于写写互斥、读写互斥、读读共享的场景， `synchronized` 无法做到读读共享，它是读读互斥，而 `Lock` 是支持读读共享的。

另外，就是 `synchronized` 抢锁是随机分配给一个线程的，有的线程可能抢了多次都抢不上，而 `Lock` 支持按请求锁的顺序分配锁，优先将锁分配给等待时间最长的线程，不过吞吐量有点低。 

最后，`synchronized` 是自动释放锁，而 `Lock` 只能手动释放，为了避免异常或Bug等问题导致没有释放锁，释放方法 `unlock` 要在 `try...finally` 语句中执行。

总的来说， `Lock` 支持 `synchronized` 的所有功能，也支持 `synchronized` 没有的功能。


Lock 接口说明
=====================

```java
public interface Lock {
    void lock();
    void lockInterruptibly() throws InterruptedException;
    boolean tryLock();
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
    void unlock();
    Condition newCondition();
}
```

使用`Lock`的一般形式：

    Lock lock = new ReentrantLock(); // 默认使用非公平锁，如果要使用公平锁，需要传入参数true  
    // ........  
    lock.lock();  
    try {  
        // 更新对象的状态。为了保证锁最终一定会被释放，要把互斥区放在 try 语句块内。
        // 捕获异常，必要时恢复到原来的不变约束。
        // 如果有 return 语句，放在这里，以确保 unlock() 不会过早发生，从而将数据暴露给第二个任务。
    finally {
        lock.unlock(); // 锁必须在 finally 块中释放。
    }

其有3个实现类：`ReentrantLock`、`ReentrantReadWriteLock.ReadLock`、`ReentrantReadWriteLock.WriteLock`。

lock()
-----------

获取锁。如果锁不可用，出于线程调度目的，将禁用当前线程，并且在获得锁之前，该线程将一直处于休眠状态。

lockInterruptibly()
-----------

如果当前线程未被中断，则获取锁。

如果锁可用，则获取锁，并立即返回。否则一直阻塞方法，直到发生以下3种情况时停止阻塞：

- 获得了锁；
- 方法被中断。

`lockInterruptibly` 与 `sleep`、`wait`、`join`方法类似，当一个线程在调用它们后阻塞线程，此时如果有另外一个线程执行了当前
线程的 `interrupt` 方法，那么此线程将被唤醒停止阻塞，但会捕获到 `InterruptedException` 异常。它不会中止线程，只是更新了
一个线程的标记而已。而 `lockInterruptibly` 与 `sleep` 这些方法一样。

tryLock()
-----------

仅在调用时锁为空闲状态才获取该锁。

如果锁可用，则获取锁，并立即返回值 true。如果锁不可用，则此方法将立即返回值 false。

一般使用方式：

    Lock lock = ...;
    if (lock.tryLock()) {
        try {
            // manipulate protected state
        } finally {
            lock.unlock();
        }
    }

tryLock(long time, TimeUnit unit)
-----------

如果锁在给定的等待时间内空闲，并且当前线程未被中断，则获取锁。

如果锁可用，则此方法将立即返回值 true。 否则一直阻塞方法，直到发生以下3种情况时停止阻塞：

- 获得了锁；
- 当前线程被中断；
- 阻塞时间超出了参数指定的时长。

unlock()
-----------

释放锁。`lock` 不会像 `synchronized` 那样自动释放锁 ，所以， 一定要放在 `finally` 块中，保证锁的释放。

newCondition()
-----------

返回绑定到此 `Lock` 实例的新 `Condition` 实例，`Condition` 比 `Lock` 能更细粒度的控制。
