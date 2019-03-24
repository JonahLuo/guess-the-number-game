package mycode.learnprogramming;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

/**
 * @author jonah
 * a implementation for the NumberGenerator
 */

public class NumberGeneratorImpl implements NumberGenerator {

    //-- fields  --
    private final Random rand = new Random();

    @Autowired
    @MaxNumber
    private int maxNumber;

    // -- public methods --
    @Override
    public int next() {
        return rand.nextInt(maxNumber);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}
