
import java.util.concurrent.RecursiveTask;

public class ParallelQuickSort  extends RecursiveTask<Integer> {
    private static final int BLOCK = 1000;

    private final int l;
    private final int r;
    int[] arr;


    public ParallelQuickSort(int l, int r, int[] arr) {
        this.l = l;
        this.r = r;
        this.arr = arr;
    }
    
    @Override
    protected Integer compute() {
        if (l >= r) return null;


        if (r - l < BLOCK) {
            SeqQuickSort seqQuickSort = new SeqQuickSort(l, r, arr);
            seqQuickSort.sort();
        }
        else {
            int p = Partition.seq_partition(l, r, arr);
            ParallelQuickSort left = new ParallelQuickSort(l, p , arr);
            ParallelQuickSort right = new ParallelQuickSort(p + 1, r, arr);

            left.fork();
            right.compute();
            left.join();
        }
        return null;

    }

}
