package hellojava.concurrent.lesson11.concurrent;

public class CustomConcurrentHelp {

    public static <T, R> CustomCallable<T, R> callable(CustomRunnable<R> task) {
        if (task == null)
            throw new NullPointerException();
        return new RunnableAdapter<T, R>(task, null);
    }

    static final class RunnableAdapter<T, R> implements CustomCallable<T, R> {
        final CustomRunnable<R> task;
        final T result;
        RunnableAdapter(CustomRunnable task, T result) {
            this.task = task;
            this.result = result;
        }
        public T call() {
            task.run();
            return result;
        }

        @Override
        public R getId() {
            return task.getId();
        }
    }
}
