Concurrent Lesson 13 说明
==========================

1 学习基本使用方式
--------------------------

通过简单的 `get()` 获取值，阻塞线程硬等到值的产生。

[CompletableFutureDemo01](src/main/java/hellojava/concurrent/lesson13/CompletableFutureDemo01.java)

2 手工设置结果数据
--------------------------

通过 `complete()` 接口传入结果数据，触发消费方获取到结果数据。

[CompletableFutureDemo02](src/main/java/hellojava/concurrent/lesson13/CompletableFutureDemo02.java)

3 学习基于回调的使用方式
--------------------------

通过简单的 `get()` 获取值，设置【成功处理函数】和【失败处理函数】，不阻塞当前线程，当发成功时执行【成功处理函数】，当失败时执行【失败处理函数】。

[CompletableFutureDemo03](src/main/java/hellojava/concurrent/lesson13/CompletableFutureDemo03.java)

4 基于回调的方式重复使用 `thenAccept`
--------------------------

可以重复使用 `thenAccept()` 传入多个【成功处理函数】，不阻塞当前线程。当发成功时会在主线程和业务线程中执行【成功处理函数】，

[CompletableFutureDemo04](src/main/java/hellojava/concurrent/lesson13/CompletableFutureDemo04.java)




