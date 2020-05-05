package encryptor.configurations;

import encryptor.generators.KeyGenerator;
import encryptor.generators.RandomKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyGeneratorConfiguration {

    @Bean("randomKeyGenerator")
    public KeyGenerator createRandomKeyGenerator() {
        return new RandomKeyGenerator();
    }
}
