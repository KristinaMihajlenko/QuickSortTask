public class SeqQuickSort {
    private int l;
    private int r;
    int[] arr;

    public SeqQuickSort(int l, int r, int[] arr) {
        this.l = l;
        this.r = r;
        this.arr = arr;
    }


    public Integer sort() {
        if (l >= r) return null;

        int p = Partition.seq_partition(l, r, arr);
        SeqQuickSort left = new SeqQuickSort(l, p , arr);
        SeqQuickSort right = new SeqQuickSort(p + 1, r, arr);
        left.sort();
        right.sort();
        return null;
    }

}
