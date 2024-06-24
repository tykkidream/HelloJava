package hellojava.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, List> map = new HashMap<>();

        System.out.println(map.toString());

        {
            List list = map.get("a");

            if (list == null) {
                list = new LinkedList();

                map.put("a", list);
            }

            list.add("abc");
            list.add("123");
        }

        System.out.println(map.toString());

        {
            List list = map.computeIfAbsent("b", MapDemo::create);

            list.add("abc");
            list.add("123");
        }

        System.out.println(map.toString());

        {
            List list = map.computeIfAbsent("b", MapDemo::create);

            list.add("qwe");
            list.add("!@#");
        }

        System.out.println(map.toString());

        {
            List list = map.computeIfAbsent("c", MapDemo::create);

            list.add("111");
            list.add("222");
        }

        System.out.println(map.toString());
    }

    private static List create(String key) {
        System.out.println("创建一个新的 list ， key = " + key);
        return new LinkedList();
    }
}
