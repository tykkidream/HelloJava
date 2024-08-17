package hellojava.v21.concurrent;

import java.util.Random;

public class Demo08 {
    /**
     * 学习 OfPlatform 的其它一些用法
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.Builder.OfPlatform platformBuilder = Thread.ofPlatform();

        platformBuilder.daemon(true);
        platformBuilder.group(Thread.currentThread().getThreadGroup());
        platformBuilder.name("平台线程名称");

        // start > 0 的情况下会覆盖上一行 name 方法属性配置
        platformBuilder.name("平台线程名称", 100);

        platformBuilder.priority(1);
        platformBuilder.stackSize(10);

        // 是否启用ThreadLocal
        //platformBuilder.allowSetThreadLocals(false);

        // 是否启用InheritableThreadLocal
        platformBuilder.inheritInheritableThreadLocals(false);

        // 设置未捕获异常处理器
        platformBuilder.uncaughtExceptionHandler((t, e) -> {
            System.out.println("收到异常！");
            System.out.println(t);
            System.out.println(t.getClass().getName());
            System.out.println(e);
            System.out.println(e.getClass().getName());

            e.printStackTrace();
        });

        // 创建不自动启动的线程
        Thread thread = platformBuilder.unstarted(() -> {
            System.out.println("开始执行虚拟线程里的逻辑！");

            Thread currentThread = Thread.currentThread();

            System.out.println("当前线程 %s  是否为虚拟线程： %s 是否为守护线程： %s".formatted(currentThread.getName(), currentThread.isVirtual(), currentThread.isDaemon()));

            Random rand = new Random();
            int randomInt = rand.nextInt(2);

            if (randomInt / 2 == 0) {
                throwException();
            }
        });

        // 手工启动线程
        thread.start();


        Thread.sleep(3000);
    }

    static void throwException() {
        throw new RuntimeException("发生异常了！");
    }
}
