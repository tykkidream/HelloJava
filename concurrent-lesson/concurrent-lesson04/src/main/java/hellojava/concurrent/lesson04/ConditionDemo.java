package hellojava.concurrent.lesson04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tykkidream on 2017/11/9.
 */
public class ConditionDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Condition condition = lock.newCondition();
    }
}
