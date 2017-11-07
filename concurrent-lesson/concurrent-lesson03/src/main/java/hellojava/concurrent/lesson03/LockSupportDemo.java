package hellojava.concurrent.lesson03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by Tykkidream on 2017/11/7.
 */
public class LockSupportDemo {

    private static Logger logger = LoggerFactory.getLogger(LockSupportDemo.class);

    private static class Data {
        String index = "";
        String data = "";
    }

    public static void main(String[] args) {

        final Data input = new Data();

        final Object blocker = new Object();


        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (!input.data.equals("exit")){
                    LockSupport.park();
                    logger.info("线程一：{}", input.data);
                }
            }
        };


        Thread thread2 = new Thread() {
            @Override
            public void run() {
                while (!input.data.equals("exit")){
                    LockSupport.park(blocker);
                    logger.info("线程二：{}", input.data);
                }
            }
        };

        thread1.start();
        thread2.start();

        while (true) {
            read(input);

            if (input.index.equals("1")) {
                LockSupport.unpark(thread1);
            } else if (input.index.equals("2")){
                LockSupport.unpark(thread2);
            }

            if (input.data.equals("exit")) {
                break;
            }
        }
    }

    private static void read(Data data) {
        Scanner scanner = new Scanner(System.in);
        data.index = scanner.nextLine();
        data.data = scanner.nextLine();
    }
}
