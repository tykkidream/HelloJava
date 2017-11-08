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
        String data = "";
    }

    public static void main(String[] args) {

        final Data input = new Data();

        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!input.data.equals("exit")){
                    LockSupport.park();
                    logger.info(input.data);
                }
            }
        };

        thread.start();

        while (true) {
            input.data = read();
            LockSupport.unpark(thread);
            if (input.data.equals("exit")) {
                break;
            }
        }
    }

    private static String read() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
