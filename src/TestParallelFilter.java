import java.util.Arrays;
import java.util.function.UnaryOperator;

public class TestParallelFilter {
    public static void main(String[] args) {
        int[] const_arr = {1, 2, 6, 7, 8, 1, 4, 3, 4, 5};
        System.out.println(Arrays.toString(const_arr));
        UnaryOperator<Integer> func = x -> x > 3 ? 1 : 0;
        ParallelFilter parallel_filter = new ParallelFilter(const_arr, func);
        int[] res = parallel_filter.compute();
        System.out.println(Arrays.toString(res));
    }
}
