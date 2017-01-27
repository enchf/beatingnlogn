package j4g.sorting;

import j4g.util.RandomArrays;
import j4g.util.Timer;

import java.util.Arrays;

/**
 * Radix sort implementation for numbers from 0 to 100000.
 */
public class RadixSort extends CountingSort {

    private int iteration;
    private static final int FACTOR = 100;
    private static final int MAX_ITER = 3;

    protected RadixSort(int min, int max) {
        super(min, max);
    }

    public static RadixSort ofRange(int min, int max) {
        return new RadixSort(min, max);
    }

    @Override
    public void sort(int[] array) {
        int[] copy = Arrays.copyOf(array, array.length);
        int[] counters = new int[FACTOR];
        iteration = 1;

        // Optimizations: determine min and max values, and dynamically adjust limits.

        for (int i = 0; i < MAX_ITER; i++) {
            Arrays.fill(counters, 0);
            classify(array, counters);
            transformCounterToIndices(counters);
            rearrange(array, copy, counters);
            System.arraycopy(array, 0, copy, 0, array.length);
            iteration *= FACTOR;
        }
    }

    protected int indexOfElement(int element) {
        // Not concurrent-safe.
        return (element / iteration) % 10;
    }

    public static void main(String...args) {
        Timer radixSort, quickSort;
        int[] rsArray, qsArray;
        int size = 10000000;
        int min = 0, max = 999999;

        rsArray = RandomArrays.randomArray(size, min, max);
        qsArray = Arrays.copyOf(rsArray, rsArray.length); //RandomArrays.randomArray(size, 0, 105);

        radixSort = Timer.with(() -> RadixSort.ofRange(min, max).sort(rsArray));
        quickSort = Timer.with(() -> Arrays.sort(qsArray));

        System.out.println("Radix sort: " + radixSort.ellapsedTime());
        System.out.println("Arrays.sort Quicksort: " + quickSort.ellapsedTime());

        //System.out.println(Arrays.toString(rsArray));
    }
}
