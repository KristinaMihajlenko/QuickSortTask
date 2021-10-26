import java.util.concurrent.ForkJoinPool;

public class ParallelScan {
    int[] arr;
    int[] sums;
    int[] res;

    public ParallelScan(int[] arr) {
        this.arr = arr;
    }

    public int[] compute() {
        sums = new int[arr.length * 2];
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Up up = new Up(arr, 0, arr.length - 1, 0, sums);
        pool.invoke(up);
        res = new int[arr.length];
        Down down = new Down(arr, sums, res, 0, arr.length - 1, 0, 0);
        pool.invoke(down);
        return res;
    }
}
