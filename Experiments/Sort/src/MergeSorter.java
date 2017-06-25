import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by wolf on 2017/5/18.
 * 归并排序
 */
public class MergeSorter implements Sorter {
    @Override
    public <T> void sort(T[] array, Comparator comparator) {
        sort(array, comparator, 0, array.length - 1);
    }

    private <T> void sort(T[] array, Comparator comparator, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = start + (end - start) / 2;
        sort(array, comparator, start, middle);
        sort(array, comparator, middle+1, end);
        merge(array, comparator, start, middle, end);
    }

    private <T> void merge(T[] array, Comparator comparator, int start, int middle, int end) {
        @SuppressWarnings("unchecked")
        T[] tmp = (T[]) new Object[end - start + 1];
        int i = start;
        int j = middle + 1;
        int tmpIndex = 0;
        while (i <= middle && j <= end) {
            if (comparator.compare(array[i], array[j]) <= 0) {
                tmp[tmpIndex++] = array[i++];
            } else {
                tmp[tmpIndex++] = array[j++];
            }
        }
        while (i <= middle) {
            tmp[tmpIndex++] = array[i++];
        }
        while (j <= end) {
            tmp[tmpIndex++] = array[j++];
        }
        tmpIndex = 0;
        for (int index = start; index <= end; index++) {
            array[index] = tmp[tmpIndex++];
        }
    }

    public static void main(String[] args) {
        SortTester.test(new MergeSorter());
    }
}
