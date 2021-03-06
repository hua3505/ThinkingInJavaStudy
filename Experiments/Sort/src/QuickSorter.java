import java.util.Comparator;

/**
 * Created by wolf on 2017/5/15.
 * 快速排序
 */
public class QuickSorter implements Sorter {
    private <T> void sort(T[] array, int start, int end, Comparator comparator) {
        if (start >= end) {
            return;
        }
        int i = start;
        int j = end;
        T divider = array[start];
        while (i < j) {
            while ((i < j) && comparator.compare(divider, array[j]) <= 0) {
                j--;
            }
            array[i] = array[j];
            while ((i < j) && comparator.compare(divider, array[i]) >= 0) {
                i++;
            }
            array[j] = array[i];
        }
        array[i] = divider;
        sort(array, start, i-1, comparator);
        sort(array, i+1, end, comparator);
    }

    @Override
    public <T> void sort(T[] array, Comparator comparator) {
        sort(array, 0, array.length - 1, comparator);
    }
}
