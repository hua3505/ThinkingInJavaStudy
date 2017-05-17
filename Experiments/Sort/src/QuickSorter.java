import java.util.Comparator;

/**
 * Created by wolf on 2017/5/15.
 * 快速排序
 */
public class QuickSorter implements Sorter {
    public <T> void sort(T[] array, int start, int end, Comparator comparator) {
        if (start >= end) {
            return;
        }
        int i = start;
        int j = end;
        int k = start;
        while (i < j) {
            if (k == i) {
                if (comparator.compare(array[k], array[j]) > 0) {
                    swap(array, k, j);
                    k = j;
                } else {
                    j--;
                }
            } else if (k == j) {
                if (comparator.compare(array[k], array[i]) < 0) {
                    swap(array, k, i);
                    k = i;
                } else {
                    i++;
                }
            }
        }
        sort(array, start, k, comparator);

        sort(array, k+1, end, comparator);
    }

    private <T> void swap(T[] array, int i, int j) {
        T tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
    }

    @Override
    public <T> void sort(T[] array, Comparator comparator) {
        sort(array, 0, array.length - 1, comparator);
    }
}
