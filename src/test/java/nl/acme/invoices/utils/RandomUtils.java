package nl.acme.invoices.utils;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by kristisvaskys on 04/11/2016.
 */
public class RandomUtils {

    private static final int MAX_RAND_INT = 10000;

    public static int getRadomInt() {
        Random random = new Random();
        return random.nextInt(MAX_RAND_INT);
    }

    public static String getRadomString() {
        Random random = new Random();
        return new BigInteger(130, random).toString(32);
    }
}
