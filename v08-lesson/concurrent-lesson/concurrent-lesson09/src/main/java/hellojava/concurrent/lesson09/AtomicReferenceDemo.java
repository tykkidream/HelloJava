package hellojava.concurrent.lesson09;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Tykkidream on 2018/1/12.
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User user = new User("conan", 15);

        AtomicReference atomicReference = new AtomicReference(user);

        System.out.println("初始");
        System.out.println(JSON.toJSONString(atomicReference.get()));

        User updateUser = new User("Shinichi", 17);

        System.out.println("\ncompareAndSet");
        atomicReference.compareAndSet(updateUser, updateUser);
        System.out.println(JSON.toJSONString(atomicReference.get()));
        atomicReference.compareAndSet(user, updateUser);
        System.out.println(JSON.toJSONString(atomicReference.get()));

        // 将位置 i 的元素以原子方式设置为给定值，并返回旧值。
        System.out.println("\ngetAndSet");
        Object andSet = atomicReference.getAndSet(user);
        System.out.println(JSON.toJSONString(andSet));
        System.out.println(JSON.toJSONString(atomicReference.get()));

        System.out.println("\nlazySet");
        atomicReference.lazySet(updateUser);
        System.out.println(JSON.toJSONString(atomicReference.get()));

        System.out.println("\nweakCompareAndSet");
        atomicReference.weakCompareAndSet(user,user);
        System.out.println(JSON.toJSONString(atomicReference.get()));
        atomicReference.weakCompareAndSet(updateUser,user);
        System.out.println(JSON.toJSONString(atomicReference.get()));
    }
}
