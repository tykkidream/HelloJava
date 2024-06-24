package hellojava.concurrent.lesson09;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * Created by Tykkidream on 2018/1/13.
 */
public class AtomicLongFieldUpdaterDemo {
    public static void main(String[] args) {

        // 原子更新的类的字段必须是volatile类型，不能是private
        AtomicLongFieldUpdater<Role> userAtomicLongFieldUpdater = AtomicLongFieldUpdater.newUpdater(Role.class, "id");

        // 2、实例化目标类
        Role role = new Role();
        role.id = 10L;

        System.out.println("初始");
        System.out.println(JSON.toJSONString(role));

        // 3、原子更新
        System.out.println("\ncompareAndSet");
        userAtomicLongFieldUpdater.compareAndSet(role, 123L, 123L);
        System.out.println(JSON.toJSONString(role));
        userAtomicLongFieldUpdater.compareAndSet(role, 10L, 123L);
        System.out.println(JSON.toJSONString(role));

        // 将位置 i 的元素以原子方式设置为给定值，并返回旧值。
        System.out.println("\ngetAndSet");
        long andSet = userAtomicLongFieldUpdater.getAndSet(role, 456L);
        System.out.println(JSON.toJSONString(andSet));
        System.out.println(JSON.toJSONString(role));

        System.out.println("\nlazySet");
        userAtomicLongFieldUpdater.lazySet(role, 789L);
        System.out.println(JSON.toJSONString(role));

        System.out.println("\nweakCompareAndSet");
        userAtomicLongFieldUpdater.weakCompareAndSet(role,123L, 123L);
        System.out.println(JSON.toJSONString(role));
        userAtomicLongFieldUpdater.weakCompareAndSet(role, 789L, 123L);
        System.out.println(JSON.toJSONString(role));
    }
}
