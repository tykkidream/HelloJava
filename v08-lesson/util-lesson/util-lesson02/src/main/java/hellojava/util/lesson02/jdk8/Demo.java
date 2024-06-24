package hellojava.util.lesson02.jdk8;

import static hellojava.util.lesson02.jdk8.HashMap.DEFAULT_INITIAL_CAPACITY;
import static hellojava.util.lesson02.jdk8.HashMap.MAXIMUM_CAPACITY;

public class Demo {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        String a = "abcdefg";

        map.put(a, "1");

        int hashCode = a.hashCode();

        System.out.println(Integer.toBinaryString(hashCode));
        System.out.println(Integer.toBinaryString(hashCode >>> 16));
        System.out.println(Integer.toBinaryString(hashCode << 15));
        System.out.println(Integer.toBinaryString(hashCode ^ (hashCode >>> 16)));

        System.out.print("DEFAULT_INITIAL_CAPACITY： ");
        System.out.println(Integer.toBinaryString(DEFAULT_INITIAL_CAPACITY));

        System.out.print("MAXIMUM_CAPACITY： ");
        System.out.println(Integer.toBinaryString(MAXIMUM_CAPACITY));

    }

}
