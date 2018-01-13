package hellojava.concurrent.lesson09;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by Tykkidream on 2018/1/13.
 */
public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {
        Role role1 = new Role();
        role1.name = "role-1";

        Role role2 = new Role();
        role2.name = "role-2";

        /*
        AtomicMarkableReference 是使用 int 来判断引用是否被改变过，且改过多少次了，也会对对象引用进行判断是否相同
        也就是说，要比较标识，比较多少次，也要比较内容
        标识仅是 int ，也就是说 AtomicMarkableReference 用来处理关心有没有被修改过，还关心被修改过几次
         */

        // 创建一个AtomicMarkableReference，初始设置引用对象为 role1 修改标识为 false 未修改
        AtomicStampedReference<Role> atomicStampedReference = new AtomicStampedReference(role1, 0);

        System.out.println("初始");
        System.out.println(atomicStampedReference.getStamp());
        System.out.println(JSON.toJSONString(atomicStampedReference.getReference()));

        System.out.println("\ncompareAndSet");
        atomicStampedReference.compareAndSet(role1, role2, 1, 1);
        System.out.println(atomicStampedReference.getStamp());
        System.out.println(JSON.toJSONString(atomicStampedReference.getReference()));

        System.out.println("\ncompareAndSet");
        atomicStampedReference.compareAndSet(role1, role2, 0, 1);
        System.out.println(atomicStampedReference.getStamp());
        System.out.println(JSON.toJSONString(atomicStampedReference.getReference()));


        System.out.println("\nweakCompareAndSet");
        System.out.println(atomicStampedReference.weakCompareAndSet(role1, role2, 1, 3));
        System.out.println(atomicStampedReference.weakCompareAndSet(role2, role1, 0, 3));
        System.out.println(atomicStampedReference.weakCompareAndSet(role2, role1, 1, 3));
        System.out.println(JSON.toJSONString(atomicStampedReference.getStamp()));
        System.out.println(JSON.toJSONString(atomicStampedReference.getReference()));


        // 如果当前引用 == 预期引用，则以原子方式将该标记的值设置为给定的更新值。
        // 仅是通过比较引用，来修改标识的。
        System.out.println("\nattemptMark");
        System.out.println(atomicStampedReference.attemptStamp(role2, 5));
        System.out.println(JSON.toJSONString(atomicStampedReference.getStamp()));
        System.out.println(atomicStampedReference.attemptStamp(role1, 5));
        System.out.println(JSON.toJSONString(atomicStampedReference.getStamp()));

    }
}
