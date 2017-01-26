package j4g.sorting;

import java.util.Arrays;

/**
 *
 */
public class CountingSort {

    private int min;
    private int max;

    private CountingSort() {}

    public static CountingSort ofRange(int min, int max) {
        CountingSort sort = new CountingSort();
        sort.min = min;
        sort.max = max;
        return sort;
    }

    public void sort(int[] array) {
        int[] counters = new int[max - min + 1];
        int prev, aux;
        int[] copy = Arrays.copyOf(array, array.length);
        int index;

        for (int i : array) counters[indexOfElement(i)]++;

        prev = counters[0];
        counters[0] = 0;

        for (int i = 1; i < counters.length; i++) {
            aux = counters[i];
            counters[i] = counters[i-1] + prev;
            prev = aux;
        }

        for (int i = 0; i < array.length; i++) {
            index = indexOfElement(copy[i]);
            array[counters[index]++] = copy[i];
        }
    }

    public int indexOfElement(int element) {
        return (element - min) % (max - min + 1);
    }
}
