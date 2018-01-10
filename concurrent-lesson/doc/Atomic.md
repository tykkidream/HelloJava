Atomic 包说明
=======================

    参考：http://ifeve.com/java-atomic/

在Atomic包里一共有12个类，四种原子更新方式，分别是如下：

- 原子更新基本类型：用于通过原子的方式更新基本类型
- 原子更新数组：通过原子的方式更新数组里的某个元素
- 原子更新引用：原子更新基本类型的AtomicInteger，只能更新一个变量，如果要原子的更新多个变量，就需要使用这个原子更新引用类型提供的类
- 原子更新字段：如果我们只需要某个类里的某个字段，那么就需要使用原子更新字段类

Atomic包里的类基本都是使用Unsafe实现的包装类。

原子更新基本类型
================================

用于通过原子的方式更新基本类型，Atomic包提供了以下三个类：

- AtomicBoolean：原子更新布尔类型。
- AtomicInteger：原子更新整型。
- AtomicLong：原子更新长整型。


原子更新数组类
========================

通过原子的方式更新数组里的某个元素，Atomic包提供了以下三个类：

- AtomicIntegerArray：原子更新整型数组里的元素。
- AtomicLongArray：原子更新长整型数组里的元素。
- AtomicReferenceArray：原子更新引用类型数组里的元素。


原子更新引用类型
=======================

原子更新基本类型的AtomicInteger，只能更新一个变量，如果要原子的更新多个变量，就需要使用这个原子更新引用类型提供的类。Atomic包提供了以下三个类：

- AtomicReference：原子更新引用类型。
- AtomicReferenceFieldUpdater：原子更新引用类型里的字段。
- AtomicMarkableReference：原子更新带有标记位的引用类型。可以原子的更新一个布尔类型的标记位和引用类型。构造方法是AtomicMarkableReference(V initialRef, boolean initialMark)


原子更新字段类
=========================

如果我们只需要某个类里的某个字段，那么就需要使用原子更新字段类，Atomic包提供了以下三个类：

- AtomicIntegerFieldUpdater：原子更新整型的字段的更新器。
- AtomicLongFieldUpdater：原子更新长整型字段的更新器。
- AtomicStampedReference：原子更新带有版本号的引用类型。该类将整数值与引用关联起来，可用于原子的更数据和数据的版本号，可以解决使用CAS进行原子更新时，可能出现的ABA问题。

方法说明
========================

| 方法 \ 类         |  AtomicBoolean  |  AtomicInteger  |  AtomicLong  |  AtomicIntegerArray  |  AtomicLongArray  |  AtomicReferenceArray  |
| :---------------- | :-------------: | :-------------: | :----------: | :------------------: | :---------------: | :--------------------: |
| compareAndSet     |        有       |        有       |      有      |          有          |         有        |            有          |
| get               |        有       |        有       |      有      |          有          |         有        |            有          |
| lazySet           |        有       |        有       |      有      |          有          |         有        |            有          |
| set               |        有       |        有       |      有      |          有          |         有        |            有          |
| weakCompareAndSet |        有       |        有       |      有      |          有          |         有        |            有          |
| getAndSet         |        有       |        有       |      有      |          有          |         有        |            有          |
| getAndAdd         |        --       |        有       |      有      |          有          |         有        |            --          |
| getAndDecrement   |        --       |        有       |      有      |          有          |         有        |            --          |
| getAndIncrement   |        --       |        有       |      有      |          有          |         有        |            --          |
| addAndGet         |        --       |        有       |      有      |          有          |         有        |            --          |
| incrementAndGet   |        --       |        有       |      有      |          有          |         有        |            --          |
| decrementAndGet   |        --       |        有       |      有      |          有          |         有        |            --          |
| doubleValue       |        --       |        有       |      有      |          --          |         --        |            --          |
| floatValue        |        --       |        有       |      有      |          --          |         --        |            --          |
| intValue          |        --       |        有       |      有      |          --          |         --        |            --          |
| longValue         |        --       |        有       |      有      |          --          |         --        |            --          |

| 方法 \ 类         |  AtomicReference  |  AtomicReferenceFieldUpdater  |  AtomicMarkableReference  |
| :---------------- | :---------------: | :---------------------------: | :-----------------------: |
| compareAndSet     |         有        |               有              |             有            |
| get               |         有        |               有              |             有            |
| lazySet           |         有        |               有              |             有            |
| set               |         有        |               有              |             有            |
| weakCompareAndSet |         有        |               有              |             有            |
| getAndSet         |         有        |               有              |             有            |
| getAndAdd         |         --        |               --              |             --            |
| getAndDecrement   |         --        |               --              |             --            |
| getAndIncrement   |         --        |               --              |             --            |
| addAndGet         |         --        |               --              |             --            |
| incrementAndGet   |         --        |               --              |             --            |
| decrementAndGet   |         --        |               --              |             --            |
| doubleValue       |         --        |               --              |             --            |
| floatValue        |         --        |               --              |             --            |
| intValue          |         --        |               --              |             --            |
| longValue         |         --        |               --              |             --            |
| newUpdater        |         --        |               有              |             --            |
| isMarked          |         --        |               --              |             有            |
| attemptStamp      |         --        |               --              |             --            |
| getReference      |         --        |               --              |             --            |
| getStamp          |         --        |               --              |             --            |


| 方法 \ 类         |  AtomicIntegerFieldUpdater  |  AtomicLongFieldUpdater  |  AtomicStampedReference  |
| :---------------- | :-------------------------: | :----------------------: | :----------------------: |
| compareAndSet     |              有             |             有           |             有           |
| get               |              有             |             有           |             有           |
| lazySet           |              有             |             有           |             --           |
| set               |              有             |             有           |             有           |
| weakCompareAndSet |              有             |             有           |             有           |
| getAndSet         |              有             |             有           |             --           |
| getAndAdd         |              有             |             有           |             --           |
| getAndDecrement   |              有             |             有           |             --           |
| getAndIncrement   |              有             |             有           |             --           |
| addAndGet         |              有             |             有           |             --           |
| incrementAndGet   |              有             |             有           |             --           |
| decrementAndGet   |              有             |             有           |             --           |
| doubleValue       |              --             |             --           |             --           |
| floatValue        |              --             |             --           |             --           |
| intValue          |              --             |             --           |             --           |
| longValue         |              --             |             --           |             --           |
| newUpdater        |              --             |             --           |             --           |
| isMarked          |              有             |             有           |             --           |
| attemptStamp      |              --             |             --           |             有           |
| getReference      |              --             |             --           |             有           |
| getStamp          |              --             |             --           |             有           |



