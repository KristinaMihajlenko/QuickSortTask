import java.util.Arrays;


public class TestParallelScan {

    public static void main(String[] args) {
        int[] const_arr = {0, 1, 1, 0, 1, 1, 0, 1};
        ParallelScan parallel_scan = new ParallelScan(const_arr);
        int[] sums = parallel_scan.compute();
        System.out.println(Arrays.toString(sums));
    }
}