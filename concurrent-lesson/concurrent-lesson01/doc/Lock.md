Lock 接口
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
