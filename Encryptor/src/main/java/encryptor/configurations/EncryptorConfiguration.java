package encryptor.configurations;

import encryptor.algorithms.EncryptionAlgorithm;
import encryptor.encryptors.FileEncryptor;
import encryptor.encryptors.RepeatEncryptor;
import encryptor.encryptors.ShiftEncryptor;
import encryptor.generators.KeyGenerator;
import encryptor.managers.FileIOHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import encryptor.pojos.EncryptorParameters;
import encryptor.ui.UIManager;

@Configuration
public class EncryptorConfiguration {

    @Autowired
    @Bean("shiftEnc")
    public FileEncryptor createShiftEncryptor(@Qualifier("shiftUp") EncryptionAlgorithm algorithm,
                                              @Qualifier("randomKeyGenerator")KeyGenerator keyGenerator,
                                              FileIOHandler fileIOHandler, UIManager uiManager, EncryptorParameters parameters) {
        return new ShiftEncryptor(algorithm, keyGenerator, fileIOHandler, uiManager, parameters);
    }

    @Autowired
    @Bean("repeatEnc")
    public FileEncryptor createRepeatEncryptor(@Qualifier("shiftUp") EncryptionAlgorithm algorithm,
                                               @Qualifier("randomKeyGenerator") KeyGenerator keyGenerator,
                                               FileIOHandler fileIOHandler, UIManager uiManager, EncryptorParameters parameters) {
        return new RepeatEncryptor(algorithm, keyGenerator, fileIOHandler, uiManager, 10, parameters);
    }
}
