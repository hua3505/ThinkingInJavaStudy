import java.util.Comparator;

/**
 * Created by wolf on 2017/5/15.
 * 排序器
 */
public interface Sorter {
    <T> void sort(T[] array, Comparator comparator);
}
