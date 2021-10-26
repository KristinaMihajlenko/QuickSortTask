import java.util.concurrent.RecursiveTask;
import java.util.function.UnaryOperator;

public class ParallelMap extends RecursiveTask<Integer> {
    private static final int MIN_TO_PARALLEL = 1000;

    private final int l;
    private final int r;
    int[] arr;
    private final UnaryOperator<Integer> f;

    @Override
    protected Integer compute() {
        if (l >= r) return null;


        if (r - l < MIN_TO_PARALLEL) {
            for (int i = l; i < r; i++){
                arr[i] = f.apply(arr[i]);
            }
            return null;
        }

        int m = (l + r) / 2;
        ParallelMap left_parallel_map = new ParallelMap(l, m , arr, f);
        ParallelMap right_parallel_map = new ParallelMap(m + 1, r, arr, f);

        left_parallel_map.fork();
        right_parallel_map.compute();
        left_parallel_map.join();

        return null;
    }

    public ParallelMap(int l, int r, int[] arr, UnaryOperator<Integer> f) {
        this.l = l;
        this.r = r;
        this.arr = arr;
        this.f = f;
    }

}
