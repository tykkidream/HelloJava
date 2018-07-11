package hellojava.concurrent.lesson11.concurrent;

import java.util.concurrent.Callable;

public interface CustomCallable<T, R> extends Callable<T> {
    R getId();
}
