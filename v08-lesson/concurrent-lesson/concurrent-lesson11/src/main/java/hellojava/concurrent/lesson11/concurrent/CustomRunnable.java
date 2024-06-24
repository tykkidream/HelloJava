package hellojava.concurrent.lesson11.concurrent;

public interface CustomRunnable<R> extends Runnable {
    R getId();
}
