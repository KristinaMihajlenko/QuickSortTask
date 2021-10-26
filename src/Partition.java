public class Partition {

    public static int seq_partition(int l, int r, int[] a) {
        int v = a[(l + r) / 2];
        int i = l;
        int j = r;
        while (i <= j) {
            while (a[i] < v) {
                i++;
            }
            while (a[j] > v) {
                j--;
            }
            if (i >= j) {
                break;
            }

            int tmp = a[j];
            a[j] = a[i];
            a[i] = tmp;
            j--;
            i++;
        }

        return j;
    }


}
