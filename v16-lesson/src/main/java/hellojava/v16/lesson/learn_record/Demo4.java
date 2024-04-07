package hellojava.v16.lesson.learn_record;

public class Demo4 {

    public Object call(int a, int b) {
        record MyRecord(int a, int b) {

        }

        return new MyRecord(a, b);
    }

    public static void main(String[] args) {
        Demo4 demo4 = new Demo4();

        Object call = demo4.call(3, 9);

        System.out.println(call.getClass());
    }
}
