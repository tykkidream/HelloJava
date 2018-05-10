package hellojava.util.lesson01;

import java.util.Observable;
import java.util.UUID;

public class RunThread extends Observable implements Runnable {

    /**
     * 此方法一经调用，立马可以通知观察者，在本例中是监听线程
     */
    public void doBusiness() {
        if(true){
            super.setChanged();
        }
        notifyObservers();
    }

    @Override
    public void run() {
        int i = 0;

        // 模拟线程运行一段时间之后退出
        while (true) {
            System.out.println("运行中 - " + i + " - " + UUID.randomUUID().toString());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                doBusiness();
                break;
            }

            i++;

            // 模拟抛出异常
            try {
                if (i == 4) {
                    String str = null;
                    // 此处将会抛出空指针异常
                    str.length();
                }
            } catch (Exception e) {
                e.printStackTrace();
                // 在抛出异常时调用，通知观察者，让其重启线程
                doBusiness();
                // 异常抛出之后，一定要跳出循环，保证将线程送进地狱
                break;
            }
        }
    }
}
