package hellojava.v08.syntax.lesson02;

import java.util.HashMap;
import java.util.Map;

public class Demo {

    public <A, B> Map<A, B> aa() {
        Map<A, B> aa = new HashMap<>();

        return aa;
    }

    public static void main(String[] args) {
        Demo demo = new Demo();

        demo.<String, String>aa().get("123");
    }
}
