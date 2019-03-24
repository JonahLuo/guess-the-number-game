package mycode.learnprogramming;

import java.util.Random;

/**
 * @author jonah
 * a implementation for the NumberGenerator
 */

public class NumberGeneratorImpl implements NumberGenerator {

    //-- fields  --
    private final Random rand = new Random();

    private int MaxNumber = 100;

    // -- public methods --
    @Override
    public int next() {
        return rand.nextInt(MaxNumber);
    }

    @Override
    public int getMaxNumber() {
        return MaxNumber;
    }
}
