package mycode.learnprogramming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author jonah
 * a implementation for the NumberGenerator
 */

@Component
public class NumberGeneratorImpl implements NumberGenerator {

    // == fields ==
    private final Random rand = new Random();

    private final int maxNumber;
    private final int minNumber;

    // == constructor ==
    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    // == public methods ==
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
