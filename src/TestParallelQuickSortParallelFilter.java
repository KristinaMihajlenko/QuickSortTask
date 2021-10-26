import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class TestParallelQuickSortParallelFilter {
    private static final int ARR_SIZE = 1000;


    private static Random random = new Random();

    private static int[] generateArray() {
        int[] res = new int[ARR_SIZE];
        for (int i = 0; i < ARR_SIZE; i++) {
            res[i] = random.nextInt();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = generateArray();
        int[] const_arr = Arrays.copyOf(array, ARR_SIZE);
//        System.out.println(Arrays.toString(const_arr));
        long start_time_par = System.nanoTime();
        ForkJoinPool pool = ForkJoinPool.commonPool();
        ParallelQuickSortParallelFilter par_quick_sort = new ParallelQuickSortParallelFilter(0, const_arr.length - 1, const_arr);
        pool.invoke(par_quick_sort);
        long par_time = System.nanoTime() - start_time_par;

        System.out.println("Iteration " + " par time: "  + par_time);
//        System.out.println(Arrays.toString(const_arr));
        Arrays.sort(array);

        boolean flag = true;

        for (int j = 0; j < ARR_SIZE; j++) {
            if (array[j] != par_quick_sort.arr[j]) {
                flag = false;
                break;
            }
        }

        if (!flag) {
            System.out.println("Incorrect par realisation");
            return;
        }

        System.out.println("Realizations are correct!");
        System.out.println("_______________________________________");
    }
}
