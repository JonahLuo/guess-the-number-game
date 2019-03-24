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
    private int MaxNumber;

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
