package hellojava.util;

public class Demo {
    public static void main(String[] args) {
        RunThread run = new RunThread();
        Listener listen = new Listener();
        run.addObserver(listen);
        new Thread(run).start();
    }
}
