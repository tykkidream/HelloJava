package hellojava.v16.lesson.learn_record;

public class Demo2 {

    public record MyRecord(int a, int b) {
        int get() {
            return a + b;
        }
    }

    public static void main(String[] args) {
        MyRecord myRecord1 = new MyRecord(5, 6);
        System.out.println(myRecord1.a);
        System.out.println(myRecord1.a());
        System.out.println(myRecord1.b);
        System.out.println(myRecord1.b());
        System.out.println(myRecord1.get());
    }
}
