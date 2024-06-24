Util Lesson 01 说明
=======================

目标
------------

- 学习使用Obserable，实现当线程异常退出后，自动重启线程。

步骤
------------

### 业务运行的类要继承Observable

线程运行的类，需要实现`Runnable`接口，并继承`Observable`的类，在`run`方法在处理异常，当发生异常时，要通知监听器：

```java
    public void run() {
        try {
            // do something
        } catch (Exception e) {
            // 通知监听器
            doBusiness();
        }
    }
```
通知监听器：

```java
    public void doBusiness() {
        if(true){
            super.setChanged();
        }
        notifyObservers();
    }
```

示例：[RunThread](src/main/java/hellojava/util/lesson01/RunThread.java)

### 创建监听器重启业务线程

创建监听器类，实现`Observer`接口，在`update`方法中添加重启线程代码。

示例：[Listener](src/main/java/hellojava/util/lesson01/Listener.java)

### 启动业务线程

1. 创建监听器实例
2. 创建业务实例
3. 将监听器实例添加给业务实例
4. 创建`Thread`实例运行

示例：[Demo](src/main/java/hellojava/util/lesson01/Demo.java)