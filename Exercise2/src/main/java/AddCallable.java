import java.util.concurrent.Callable;

public class AddCallable implements Callable<Integer> {
    private final int n;

    public AddCallable(int n){
        this.n = n;
    }

    @Override
    public Integer call() {

        int res = 0;
        for(int i = 1; i<(n+1); i++){
            res += i;
        }

        return res;
    }
}
