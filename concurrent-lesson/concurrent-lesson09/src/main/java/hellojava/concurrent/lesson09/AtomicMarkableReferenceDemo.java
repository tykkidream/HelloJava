package hellojava.concurrent.lesson09;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * Created by Tykkidream on 2018/1/12.
 */
public class AtomicMarkableReferenceDemo {
    public static void main(String[] args) {
        Role role1 = new Role();
        role1.name = "role-1";

        Role role2 = new Role();
        role2.name = "role-2";

        /*
        AtomicMarkableReference 是使用 boolean 来判断引用是否被改变过，且也会对对象引用进行判断是否相同
        也就是说，要比较标识，也要比较内容
        标识仅是 boolean ，也就是说 AtomicMarkableReference 用来处理只关心有没有被修改过，不关心被修改过几次，这是与版本号的区别
         */

        // 创建一个AtomicMarkableReference，初始设置引用对象为 role1 修改标识为 false 未修改
        AtomicMarkableReference<Role> atomicMarkableReference = new AtomicMarkableReference(role1, false);

        System.out.println("初始");
        System.out.println(atomicMarkableReference.isMarked());
        System.out.println(JSON.toJSONString(atomicMarkableReference.getReference()));

        System.out.println("\ncompareAndSet");
        atomicMarkableReference.compareAndSet(role1, role2,true,true);
        System.out.println(atomicMarkableReference.isMarked());
        System.out.println(JSON.toJSONString(atomicMarkableReference.getReference()));

        System.out.println("\ncompareAndSet");
        atomicMarkableReference.compareAndSet(role1, role2,false,true);
        System.out.println(atomicMarkableReference.isMarked());
        System.out.println(JSON.toJSONString(atomicMarkableReference.getReference()));


        System.out.println("\nweakCompareAndSet");
        System.out.println(atomicMarkableReference.weakCompareAndSet(role1, role2,true,false));
        System.out.println(atomicMarkableReference.weakCompareAndSet(role2, role1,false,true));
        System.out.println(atomicMarkableReference.weakCompareAndSet(role2, role1,true,false));
        System.out.println(JSON.toJSONString(atomicMarkableReference.isMarked()));
        System.out.println(JSON.toJSONString(atomicMarkableReference.getReference()));

    }
}
