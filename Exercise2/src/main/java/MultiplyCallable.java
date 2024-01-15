import java.util.concurrent.Callable;

public class MultiplyCallable implements Callable<Integer> {
    private final int n;
    private final int m;

    public MultiplyCallable(int n, int m) {
        this.n = n;
        this.m = m;
    }

    @Override
    public Integer call() {
        return n * m;
    }
}