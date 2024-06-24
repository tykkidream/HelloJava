package hellojava.concurrent.lesson09;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by Tykkidream on 2018/1/13.
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static void main(String[] args) {

        AtomicIntegerFieldUpdater<User> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");

        // 2、实例化目标类
        User user = new User("conan", 10);

        // 原子更新的类的字段必须是volatile类型，不能是private

        System.out.println("初始");
        System.out.println(JSON.toJSONString(user));

        // 3、原子更新
        System.out.println("\ncompareAndSet");
        atomicIntegerFieldUpdater.compareAndSet(user, 123, 123);
        System.out.println(JSON.toJSONString(user));
        atomicIntegerFieldUpdater.compareAndSet(user, 10, 123);
        System.out.println(JSON.toJSONString(user));

        // 将位置 i 的元素以原子方式设置为给定值，并返回旧值。
        System.out.println("\ngetAndSet");
        int andSet = atomicIntegerFieldUpdater.getAndSet(user, 456);
        System.out.println(JSON.toJSONString(andSet));
        System.out.println(JSON.toJSONString(user));

        System.out.println("\nlazySet");
        atomicIntegerFieldUpdater.lazySet(user, 789);
        System.out.println(JSON.toJSONString(user));

        System.out.println("\nweakCompareAndSet");
        atomicIntegerFieldUpdater.weakCompareAndSet(user,123, 123);
        System.out.println(JSON.toJSONString(user));
        atomicIntegerFieldUpdater.weakCompareAndSet(user, 789, 123);
        System.out.println(JSON.toJSONString(user));
    }
}
