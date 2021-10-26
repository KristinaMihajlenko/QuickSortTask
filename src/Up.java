import java.util.concurrent.RecursiveTask;

public class Up extends RecursiveTask<Integer> {

    int[] arr;
    int[] sums;
    int l;
    int r;
    int i;
    
    public Up(int[] arr, int l, int r, int i, int[] sums) {
        this.arr = arr;
        this.l = l;
        this.r = r;
        this.i = i;
        this.sums = sums;
    }
    protected Integer compute() {
        if (l == r) {
            return arr[l];
        }

        int m = (l + r) / 2;
        Up left = new Up(arr, l, m, 2 * i + 1, sums);
        Up right = new Up(arr, m + 1, r, 2 * i + 2, sums);
        left.fork();
        int right_res = right.compute();
        int left_res = left.join();
        sums[i] = right_res + left_res;
        return sums[i];
    }
    
}
