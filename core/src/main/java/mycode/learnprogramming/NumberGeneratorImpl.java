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

    @Autowired
    @MinNumber
    private int minNumber;

    // -- public methods --
    @Override
    public int next() {
        int bound = maxNumber-minNumber;
        return rand.nextInt(bound)+minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }
}
