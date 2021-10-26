import java.util.concurrent.RecursiveTask;

public class Down extends RecursiveTask<Integer> {
    int[] arr;
    int[] sums;
    int[] res;
    int l;
    int r;
    int i;
    int left_sum;

    public Down(int[] arr, int[] sums, int[] res, int l, int r, int i, int left_sum) {
        this.arr = arr;
        this.sums = sums;
        this.res = res;
        this.l = l;
        this.r = r;
        this.i = i;
        this.left_sum = left_sum;
    }

    protected Integer compute() {
        if (l == r) {
            res[l] = arr[l] + left_sum;
            return null;
        }

        int m = (l + r) / 2;
        Down left = new Down(arr, sums, res, l, m, 2 * i + 1, left_sum);
        int new_sum = left_sum;
        if (l + 1 == r) {
            new_sum += arr[l];
        } else {
            new_sum += sums[2 * i + 1];
        }
        Down right = new Down(arr, sums, res, m + 1, r, 2 * i + 2, new_sum);
        left.fork();
        right.compute();
        left.join();
        return null;

    }
}