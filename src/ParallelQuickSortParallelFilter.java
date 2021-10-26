import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.UnaryOperator;

public class ParallelQuickSortParallelFilter extends RecursiveTask<Integer> {
    private static final int BLOCK = 1000;

    private final int l;
    private final int r;
    int[] arr;


    public ParallelQuickSortParallelFilter(int l, int r, int[] arr) {
        this.l = l;
        this.r = r;
        this.arr = arr;
    }

    @Override
    protected Integer compute() {
        if (r - l < BLOCK) {
            SeqQuickSort seqQuickSort = new SeqQuickSort(l, r, arr);
            seqQuickSort.sort();
            return null;
        }

        int midle_var = arr[(l + r) / 2];
        int[] left, middle, right;

        UnaryOperator<Integer> func;
        func = x -> x < midle_var ? 1 : 0;
        left = new ParallelFilter(arr, func).compute();
        func = x -> x == midle_var ? 1 : 0;
        middle = new ParallelFilter(arr, func).compute();
        func = x -> x > midle_var ? 1 : 0;
        right = new ParallelFilter(arr, func).compute();


        ParallelQuickSortParallelFilter left_part = new ParallelQuickSortParallelFilter(0, left.length - 1, left);
        ParallelQuickSortParallelFilter right_part = new ParallelQuickSortParallelFilter(0, right.length - 1, right);
        left_part.fork();
        right_part.compute();
        left_part.join();

        ForkJoinPool pool = ForkJoinPool.commonPool();
        QuickSortParallelFor merge_arr = new QuickSortParallelFor(0, left.length - 1, arr, 0, left);
        pool.invoke(merge_arr);
        merge_arr = new QuickSortParallelFor(0, middle.length - 1, arr, left.length, middle);
        pool.invoke(merge_arr);
        merge_arr = new QuickSortParallelFor(0, right.length - 1, arr, left.length  + middle.length, right);
        pool.invoke(merge_arr);
        return null;
    }
}
