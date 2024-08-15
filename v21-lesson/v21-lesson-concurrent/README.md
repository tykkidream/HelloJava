说明
================================

- [Demo01.java](src%2Fmain%2Fjava%2Fhellojava%2Fv21%2Fconcurrent%2FDemo01.java): 简单使用虚拟线程 `ofVirtual`。
- [Demo02.java](src%2Fmain%2Fjava%2Fhellojava%2Fv21%2Fconcurrent%2FDemo02.java): 简单使用真实线程的新 API `ofPlatform`。
- [Demo03.java](src%2Fmain%2Fjava%2Fhellojava%2Fv21%2Fconcurrent%2FDemo03.java): 简单使用虚拟线程池 `newVirtualThreadPerTaskExecutor`。
- [Demo04.java](src%2Fmain%2Fjava%2Fhellojava%2Fv21%2Fconcurrent%2FDemo04.java): 使用 `startVirtualThread` 启动虚拟线程。
- [Demo05.java](src%2Fmain%2Fjava%2Fhellojava%2Fv21%2Fconcurrent%2FDemo05.java): 为虚拟线程起名字。
- [Demo06.java](src%2Fmain%2Fjava%2Fhellojava%2Fv21%2Fconcurrent%2FDemo06.java): 使用工厂 `factory` 创建虚拟线程。

至此，学习到虚拟线程的 4 种创建方式：
1. ofVirtual
2. 静态方法：startVirtualThread
3. 工厂方式：factory
4. 线程池：newVirtualThreadPerTaskExecutor


- [Demo07.java](src%2Fmain%2Fjava%2Fhellojava%2Fv21%2Fconcurrent%2FDemo07.java): 学习 `OfVirtual` 的其它一些用法。