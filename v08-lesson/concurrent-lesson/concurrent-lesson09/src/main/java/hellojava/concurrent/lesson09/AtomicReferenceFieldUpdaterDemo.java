package hellojava.concurrent.lesson09;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by Tykkidream on 2018/1/12.
 */
public class AtomicReferenceFieldUpdaterDemo {
    public static void main(String[] args) {

        // 1、调用AtomicReferenceFieldUpdater类的静态newUpdater方法实例化，传入需要原子性更新属性的User类、被更新属性类型为String，名称为name：
        AtomicReferenceFieldUpdater<Role, String> atomicReferenceFieldUpdaterFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Role.class, String.class, "name");

        // 2、实例化目标类
        Role role = new Role();
        // 原子更新的类的字段必须是volatile类型，不能是private
        role.name = "conan";

        System.out.println("初始");
        System.out.println(JSON.toJSONString(role));

        // 3、原子更新
        System.out.println("\ncompareAndSet");
        atomicReferenceFieldUpdaterFieldUpdater.compareAndSet(role, "123", "123");
        System.out.println(JSON.toJSONString(role));
        atomicReferenceFieldUpdaterFieldUpdater.compareAndSet(role, "conan", "123");
        System.out.println(JSON.toJSONString(role));

        // 将位置 i 的元素以原子方式设置为给定值，并返回旧值。
        System.out.println("\ngetAndSet");
        String andSet = atomicReferenceFieldUpdaterFieldUpdater.getAndSet(role, "cat");
        System.out.println(JSON.toJSONString(andSet));
        System.out.println(JSON.toJSONString(role));

        System.out.println("\nlazySet");
        atomicReferenceFieldUpdaterFieldUpdater.lazySet(role, "tom");
        System.out.println(JSON.toJSONString(role));

        System.out.println("\nweakCompareAndSet");
        atomicReferenceFieldUpdaterFieldUpdater.weakCompareAndSet(role,"wiki", "wiki");
        System.out.println(JSON.toJSONString(role));
        atomicReferenceFieldUpdaterFieldUpdater.weakCompareAndSet(role,"tom", "wiki");
        System.out.println(JSON.toJSONString(role));
    }
}
