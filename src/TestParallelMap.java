import java.util.Arrays;

import java.util.function.UnaryOperator;

public class TestParallelMap {

    public static void main(String[] args) {
        int[] const_arr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(const_arr));
        int[] flags = Arrays.copyOf(const_arr, const_arr.length);
        //map
        UnaryOperator<Integer> func = x -> x > 3 ? 1 : 0;
        ParallelMap parallel_map = new ParallelMap(0, const_arr.length, flags, func);
        parallel_map.compute();
        System.out.println(Arrays.toString(flags));
    }
}
