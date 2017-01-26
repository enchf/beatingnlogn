package j4g.beatnlogn;

import j4g.sorting.CountingSort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ernesto_Espinosa on 1/26/2017.
 */
public class Main {

    public static int[] randomArray(int size, int min, int max) {
        return new Random().ints(min, max + 1).limit(size).toArray();
    }

    public static void main(String...args) {
        int min = 3, max = 10;
        int[] testArray = randomArray(10, min, max);
        CountingSort sort = CountingSort.ofRange(min, max);

        System.out.println(Arrays.toString(testArray));
        sort.sort(testArray);
        System.out.println(Arrays.toString(testArray));
    }
}
