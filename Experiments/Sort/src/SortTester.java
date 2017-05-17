import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by wolf on 2017/5/15.
 * 测试排序算法
 */
public class SortTester {
    public static void main(String[] args) {
        test(new QuickSorter());
    }

    private static void test(Sorter sorter) {
        Integer[] a = {1, 23, 1233, 0, -9, 32, 23, 5, 5, 67};
        sorter.sort(a, Comparator.naturalOrder());
        System.out.println(Arrays.toString(a));
    }
}
