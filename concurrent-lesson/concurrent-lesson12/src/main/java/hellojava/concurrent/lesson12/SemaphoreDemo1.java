package hellojava.concurrent.lesson12;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo1 implements Runnable {
    private Semaphore semaphore;

    private Integer no;

    private SemaphoreDemo1(Semaphore semaphore, Integer no) {
        this.semaphore = semaphore;
        this.no = no;
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        SemaphoreDemo1 demo1 = new SemaphoreDemo1(semaphore, 1);
        SemaphoreDemo1 demo2 = new SemaphoreDemo1(semaphore, 2);
        SemaphoreDemo1 demo3 = new SemaphoreDemo1(semaphore, 3);
        SemaphoreDemo1 demo4 = new SemaphoreDemo1(semaphore, 4);
        SemaphoreDemo1 demo5 = new SemaphoreDemo1(semaphore, 5);

        Thread thread1 = new Thread(demo1);
        Thread thread2 = new Thread(demo2);
        Thread thread3 = new Thread(demo3);
        Thread thread4 = new Thread(demo4);
        Thread thread5 = new Thread(demo5);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();

            System.out.println("编号 " + no + " 获取了许可！");

        } catch (Throwable throwable) {
            System.out.println("编号 " + no + " 获取许可异常！");
            throwable.printStackTrace();
            return;
        }

        try {
            int availablePermits = semaphore.availablePermits();
            System.out.println("编号 " + no + " 当前可用许可数： " + availablePermits);
            Thread.sleep(2000L);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        try {
            semaphore.release();

            System.out.println("编号 " + no + " 释放了许可！");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
