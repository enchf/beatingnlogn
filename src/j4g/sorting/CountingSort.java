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

    protected CountingSort(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static CountingSort ofRange(int min, int max) {
        return new CountingSort(min, max);
    }

    public void sort(int[] array) {
        int[] counters = new int[max - min + 1];
        int[] copy = Arrays.copyOf(array, array.length);

        classify(array, counters);
        transformCounterToIndices(counters);
        rearrange(array, copy, counters);
    }

    protected void classify(int[] array, int[] counters) {
        for (int i : array) counters[indexOfElement(i)]++;
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

    protected void rearrange(int[] array, int[] copy, int[] counters) {
        int index;
        for (int i = 0; i < array.length; i++) {
            index = indexOfElement(copy[i]);
            array[counters[index]++] = copy[i];
        }
    }

    protected int indexOfElement(int element) {
        return (element - min) % (max - min + 1);
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public static void main(String...args) {
        Timer countingSort, quickSort;
        int[] csArray, qsArray;
        int size = 10000000;
        int min = 0, max = 105;

        csArray = RandomArrays.randomArray(size, 0, 105);
        qsArray = Arrays.copyOf(csArray, csArray.length); //RandomArrays.randomArray(size, 0, 105);

        countingSort = Timer.with(() -> CountingSort.ofRange(min, max).sort(csArray));
        quickSort = Timer.with(() -> Arrays.sort(qsArray));

        System.out.println("Counting sort: " + countingSort.ellapsedTime());
        System.out.println("Arrays.sort Quicksort: " + quickSort.ellapsedTime());

        //System.out.println(Arrays.toString(csArray));
    }
}
