import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.function.UnaryOperator;

public class ParallelFilter {

    int[] arr;
    int[] res;
    int[] flags;
    int[] sums;
    private final UnaryOperator<Integer> f;

    public ParallelFilter(int[] arr, UnaryOperator<Integer> f) {
        this.arr = arr;
        this.f = f;
    }
    public int[] compute() {
        flags = Arrays.copyOf(arr, arr.length);

        ForkJoinPool pool = ForkJoinPool.commonPool();
        ParallelMap parallel_map = new ParallelMap(0, arr.length, flags, f);
        pool.invoke(parallel_map);


        ParallelScan parallel_scan = new ParallelScan(flags);
        sums = parallel_scan.compute();
        res = new int[]{};
        if (sums.length >= 2) {
            res = new int[sums[sums.length - 2] + 1];
        }
        if (sums.length == 1) {
            res = new int[]{1};
        }
        FilterParallelFor filterParallelFor = new FilterParallelFor(0, arr.length - 1, arr, flags, sums, res);
        pool.invoke(filterParallelFor);

        return res;
    }
}
