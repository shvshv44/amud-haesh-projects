package generators;

import java.util.Random;

public class RandomKeyGenerator extends KeyGenerator {
    private Random rnd;

    public RandomKeyGenerator() {
        this.rnd = new Random();
    }

    @Override
    public int makeKey() {
        return rnd.nextInt();
    }
}
