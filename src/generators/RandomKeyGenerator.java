package generators;

import java.util.Random;

public class RandomKeyGenerator implements KeyGenerator {
    private Random rnd;
    public RandomKeyGenerator() {
        this.rnd = new Random();
    }

    @Override
    public int generateKey() {
        return rnd.nextInt();
    }
}
