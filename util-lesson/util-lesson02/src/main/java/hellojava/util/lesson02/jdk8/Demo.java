package hellojava.util.lesson02.jdk8;

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
    }
}
