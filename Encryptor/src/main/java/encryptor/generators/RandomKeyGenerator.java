package encryptor.generators;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomKeyGenerator implements KeyGenerator {
    private Random rnd;

    public RandomKeyGenerator() {
        this.rnd = new Random();
    }

    @Override
    public int makeKey() {
        return rnd.nextInt();
    }
}
