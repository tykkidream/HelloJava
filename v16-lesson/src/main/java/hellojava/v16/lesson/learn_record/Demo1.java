package hellojava.v16.lesson.learn_record;

public record Demo1(int a, int b) {
    int get() {
        return a + b;
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1(3, 6);
        System.out.println(demo1.a);
        System.out.println(demo1.b);
        System.out.println(demo1.get());
    }
}
