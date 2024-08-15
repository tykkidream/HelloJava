package hellojava.v21.concurrent;

import java.util.Random;

public class Demo07 {
    /**
     * 学习 OfVirtual 的其它一些用法
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.Builder.OfVirtual virtualBuild = Thread.ofVirtual();

        virtualBuild.name("虚拟线程名称");
        // start > 0 的情况下会覆盖上一行 name 方法属性配置
        virtualBuild.name("虚拟线程名称", 100);

        // 是否启用ThreadLocal
        //virtualBuild.allowSetThreadLocals(false);

        // 是否启用InheritableThreadLocal
        virtualBuild.inheritInheritableThreadLocals(false);

        // 设置未捕获异常处理器
        virtualBuild.uncaughtExceptionHandler((t, e) -> {
            System.out.println("收到异常！");
            System.out.println(t);
            System.out.println(t.getClass().getName());
            System.out.println(e);
            System.out.println(e.getClass().getName());

            e.printStackTrace();
        });


        virtualBuild.start(() -> {
            System.out.println("开始执行虚拟线程里的逻辑！");

            Random rand = new Random();
            int randomInt = rand.nextInt(2);

            if (randomInt / 2 == 0) {
                throwException();
            }
        });

        Thread.sleep(3000);
    }

    static void throwException() {
        throw new RuntimeException("发生异常了！");
    }
}
