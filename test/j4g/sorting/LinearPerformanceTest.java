package j4g.sorting;

import j4g.util.RandomArrays;
import j4g.util.Timer;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nacho on 14/02/17.
 */
public class LinearPerformanceTest {

    private int[] data;
    private long quickSortMs;

    private static final int SIZE = 10000000; //10M.
    private static final int CS_RANGE = 1000; //1K.
    private static final int RS_RANGE = 10000000; // Full.

    @Before
    public void setup() {
        int[] qsData;

        qsData = new int[SIZE];
        data = RandomArrays.randomArray(SIZE, 0, RS_RANGE);
        System.arraycopy(data, 0, qsData, 0, SIZE);

        quickSortMs = Timer
                .with(() -> Arrays.sort(qsData))
                .ellapsedTime();
    }

    @Test
    public void testRadixSort() {

    }

    @Test
    public void testCountingSort() {

    }
}
