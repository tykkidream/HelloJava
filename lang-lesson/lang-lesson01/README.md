学习 InheritableThreadLocal
==========================================


Demo 1
---------------------------------

此Demo演示了InheritableThreadLocal基本用法和特性：

主线程首先有值，其子线程将继承其值，之后主线程对值的任何修改，子线程不可见，子线程对其值的修改，主线程也不可见。

文件：[InheritableThreadLocalDemo1.java](src/main/java/hellojava/lang/lesson01/InheritableThreadLocalDemo1.java)



Demo 2
---------------------------------

这个Demo主要想说明线程池中的线程一开始也是从主线程中继承值，但由于线程复用，之后这个值就会被不同的任务中传递。

文件：[InheritableThreadLocalDemo2.java](src/main/java/hellojava/lang/lesson01/InheritableThreadLocalDemo2.java)
