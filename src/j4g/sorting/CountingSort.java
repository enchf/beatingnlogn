package j4g.sorting;

import j4g.util.RandomArrays;
import j4g.util.Timer;

import java.util.Arrays;

/**
 * Counting sort implementation. Useful for short ranges (1-1000).
 */
public class CountingSort implements IntSort {

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

        int[] copy = Arrays.copyOf(array, array.length);
        int index;

        for (int i : array) counters[indexOfElement(i)]++;
        transformCounterToIndices(counters);

        for (int i = 0; i < array.length; i++) {
            index = indexOfElement(copy[i]);
            array[counters[index]++] = copy[i];
        }
    }

    protected void transformCounterToIndices(int[] counters) {
        int prev, aux;

        prev = counters[0];
        counters[0] = 0;

        for (int i = 1; i < counters.length; i++) {
            aux = counters[i];
            counters[i] = counters[i-1] + prev;
            prev = aux;
        }
    }

    public int indexOfElement(int element) {
        return (element - min) % (max - min + 1);
    }

    public static void main(String...args) {
        Timer countingSort, quickSort;
        int[] csArray, qsArray;
        int size = 5000000;
        int min = 0, max = 105;

        csArray = RandomArrays.randomArray(size, 0, 105);
        qsArray = RandomArrays.randomArray(size, 0, 105);

        countingSort = Timer.with(() -> CountingSort.ofRange(min, max).sort(csArray));
        quickSort = Timer.with(() -> Arrays.sort(qsArray));

        System.out.println("Counting sort: " + countingSort.ellapsedTime());
        System.out.println("Arrays.sort Quicksort: " + quickSort.ellapsedTime());

        //System.out.println(Arrays.toString(csArray));
    }
}
