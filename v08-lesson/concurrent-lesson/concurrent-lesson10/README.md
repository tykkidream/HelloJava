Executors 学习
==========================

此项目只是初步认识下 `Executors` ，简单使用了其 `newFixedThreadPool`、`newCachedThreadPool`、`newScheduledThreadPool`、`newSingleThreadExecutor`。


代码：
- [`Executors.newCachedThreadPool()`](src/main/java/hellojava/concurrent/lesson10/CachedThreadPoolDemo.java)
- [`Executors.newFixedThreadPool()`](src/main/java/hellojava/concurrent/lesson10/FixedThreadPoolDemo.java)
- [`Executors.newScheduledThreadPool()`](src/main/java/hellojava/concurrent/lesson10/ScheduledThreadPoolDemo.java)
- [`Executors.newSingleThreadExecutor()`](src/main/java/hellojava/concurrent/lesson10/SingleThreadExecutorDemo.java)


newSingleThreadExecutor
-----------------------------------------------------
 
创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。

newFixedThreadPool
-----------------------------------------------------
 
创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。线程池的大小一旦达到最大值就会保持不变，在提交新任务，任务将会进入等待队列中等待。如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。

newCachedThreadPool
-----------------------------------------------------

创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，

那么就会回收部分空闲（60秒处于等待任务到来）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。此线程池的最大值是Integer的最大值(2^31-1)。

newScheduledThreadPool
------------------------------------------------------

创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。

 