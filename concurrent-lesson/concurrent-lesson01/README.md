并发问题示例
=====================

先看一个不使用锁的并发示例，数据结果是有问题的：

代码示例：[NoLockDemo](src/main/java/hellojava/concurrent/lesson01/NoLockDemo.java)

正确结果：应为10个元素，分别是线程1和2，各5个值。

错误情况：1、有时会出现`java.util.ConcurrentModificationException`异常；2、数据丢失，某次测试最后只有9个元素，[查看图片1](doc/NoLockDemoError1.PNG)，[查看图片2](doc/NoLockDemoError2.PNG)，[查看图片3](doc/NoLockDemoError3.PNG)。

使用 ReentrantLock 解决并发问题
=====================

使用 `lock()` 来实现普通的锁功能。多个线程同时抢锁，只有一个抢成功，释放后，其它线程再抢。

- 代码示例：[ReentrantLockDemo1](src/main/java/hellojava/concurrent/lesson01/reentrant/ReentrantLockDemo1.java)

使用 `lockInterruptibly()` 来实现可中断的锁功能。多个线程同时抢锁，当执行了其中一个线程的 `interrupt` 方法后，那个线程将退出抢锁。

- 代码示例：[ReentrantLockDemo2](src/main/java/hellojava/concurrent/lesson01/reentrant/ReentrantLockDemo2.java)

使用 `tryLock()` 来实现尝试性的抢锁功能，多个线程同时抢锁，只有一个抢成功，其它不会阻塞，直接退出任务。

- 代码示例：[ReentrantLockDemo3](src/main/java/hellojava/concurrent/lesson01/reentrant/ReentrantLockDemo3.java)

使用 `tryLock(long, TimeUnit)` 来实现尝试性的抢锁功能，多个线程同时抢锁，并在指定时间内，当一个线程抢到后其它线程会阻塞，如果超出时间，直接退出任务。

- 代码示例：[ReentrantLockDemo4](src/main/java/hellojava/concurrent/lesson01/reentrant/ReentrantLockDemo4.java)

使用 `ReentrantLock` 提供的一些方法来实现监控功能。

- 代码示例：[ReentrantLockDemo5](src/main/java/hellojava/concurrent/lesson01/reentrant/ReentrantLockDemo5.java)

理解 `ReentrantLock` 的重入功能。

- 代码示例：[ReentrantLockDemo6](src/main/java/hellojava/concurrent/lesson01/reentrant/ReentrantLockDemo6.java)

