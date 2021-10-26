import java.util.concurrent.RecursiveTask;

public class FilterParallelFor extends RecursiveTask<Integer> {

    private final int l;
    private final int r;
    int[] arr;
    int[] flags;
    int[] sums;
    int[] res;

    @Override
    protected Integer compute() {
        if (r == l) {
            if (flags[l] == 1){
                int ind;
                ind = l == 0 ? 0 : sums[l - 1];
                res[ind] = arr[l];
            }
            return null;
        }

        int m = (l + r) / 2;
        FilterParallelFor left_filter_parallel_for = new FilterParallelFor(l, m , arr, flags, sums, res);
        FilterParallelFor right_filter_parallel_for = new FilterParallelFor(m + 1, r, arr, flags, sums, res);

        left_filter_parallel_for.fork();
        right_filter_parallel_for.compute();
        left_filter_parallel_for.join();

        return null;
    }

    public FilterParallelFor(int l, int r, int[] arr, int[] flags, int[]sums, int[] rez) {
        this.l = l;
        this.r = r;
        this.arr = arr;
        this.flags = flags;
        this.sums = sums;
        this.res = rez;
    }

}
