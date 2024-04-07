package hellojava.v16.lesson.learn_record;

public class Demo3 {
    public record MyRecord(int a, int b) {

    }

    public MyRecord call(int a, int b) {

        return new MyRecord(a, b);
    }
}
