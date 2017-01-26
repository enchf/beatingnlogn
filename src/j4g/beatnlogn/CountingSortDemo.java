package j4g.beatnlogn;

import j4g.sorting.CountingSort;
import j4g.util.Timer;

import java.util.Arrays;
import java.util.Random;

/**
 * Test cases.
 */
public class CountingSortDemo {

    public static int[] randomArray(int size, int min, int max) {
        return new Random().ints(size, min, max + 1).toArray();
    }

    public static void main(String...args) {
        Timer countingSort, quickSort;
        int[] csArray, qsArray;
        int size = 10000000;
        int min = 0, max = 105;

        csArray = randomArray(size, 0, 105);
        qsArray = randomArray(size, 0, 105);

        countingSort = Timer.with(() -> CountingSort.ofRange(min, max).sort(csArray));
        quickSort = Timer.with(() -> Arrays.sort(qsArray));

        System.out.println("Counting sort: " + countingSort.ellapsedTime());
        System.out.println("Arrays.sort Quicksort: " + quickSort.ellapsedTime());
    }
}
