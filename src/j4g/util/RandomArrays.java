package j4g.util;

import java.util.Random;

/**
 * Created by Ernesto_Espinosa on 1/26/2017.
 */
public interface RandomArrays {

    static int[] randomArray(int size, int min, int max) {
        return new Random().ints(size, min, max + 1).toArray();
    }
}
