import java.util.concurrent.RecursiveTask;

public class QuickSortParallelFor extends RecursiveTask<Integer> {

    private final int start;
    int[] arr;
    int l;
    int r;
    int[] copy_arr;

    public QuickSortParallelFor(int l, int r, int[] arr, int start, int[] copy_arr) {
        this.arr = arr;
        this.start = start;
        this.copy_arr = copy_arr;
    }

    @Override
    protected Integer compute() {
        if (r == l) {
            arr[l + start] = copy_arr[l];
            return null;
        }

        int m = (l + r) / 2;
        QuickSortParallelFor leftMap = new QuickSortParallelFor(l, m , arr, start, copy_arr);
        QuickSortParallelFor rightMap = new QuickSortParallelFor(m + 1, r, arr, start, copy_arr);

        leftMap.fork();
        rightMap.compute();
        leftMap.join();

        return null;
    }


}
