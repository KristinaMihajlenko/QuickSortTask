import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static final int ARR_SIZE = 100000000;


    private static Random random = new Random();

    private static int[] generateArray() {
        int[] res = new int[ARR_SIZE];
        for (int i = 0; i < ARR_SIZE; i++) {
            res[i] = random.nextInt();
        }
        return res;
    }

    public static void main(String[] args) {

        long all_seq_time = 0;
        long all_par_time = 0;

        for (int i = 0; i < 5; i++) {
            int[] array = generateArray();
            
            int[] seq_array = Arrays.copyOf(array, ARR_SIZE);
            int[] par_array = Arrays.copyOf(array, ARR_SIZE);

            long start_time_par = System.nanoTime();

            ForkJoinPool pool = ForkJoinPool.commonPool();
            ParallelQuickSort par_quick_sort = new ParallelQuickSort(0, ARR_SIZE - 1, par_array);
            pool.invoke(par_quick_sort);

            long par_time = System.nanoTime() - start_time_par;

            all_par_time += par_time;
            System.out.println("Iteration " + (i+1) + " par time: "  + par_time);

            long start_time_seq = System.nanoTime();

            SeqQuickSort seq_quick_sort = new SeqQuickSort(0, ARR_SIZE - 1, seq_array);
            seq_quick_sort.sort();

            long seq_time = System.nanoTime() - start_time_seq;

            all_seq_time += seq_time;
            System.out.println("Iteration " + (i+1) + " seq time: "  + seq_time);

            boolean flag = true;
            Arrays.sort(array);

            for (int j = 0; j < ARR_SIZE; j++) {
                if (array[j] != seq_quick_sort.arr[j]) {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                System.out.println("Incorrect seq realisation");
                return;
            }

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
        
        Double mean_seq_time = all_seq_time / 5.0;
        Double mean_par_time = all_par_time / 5.0;

        System.out.println("Mean seq time: " + mean_seq_time);
        System.out.println("Mean par time: " + mean_par_time);
        System.out.println("Par quicksort is better than seq in: " + mean_seq_time / mean_par_time);
    }
}
