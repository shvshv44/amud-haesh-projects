package encryptor.configurations;

import encryptor.algorithms.EncryptionAlgorithm;
import encryptor.encryptors.FileEncryptor;
import encryptor.encryptors.RepeatEncryptor;
import encryptor.encryptors.ShiftEncryptor;
import encryptor.generators.KeyGenerator;
import encryptor.listeners.Observer;
import encryptor.managers.FileIOHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import encryptor.pojos.EncryptorParameters;
import encryptor.ui.UIManager;

import java.util.List;

@Configuration
public class EncryptorConfiguration {

    @Autowired
    @Bean("shiftEnc")
    public FileEncryptor createShiftEncryptor(@Qualifier("shiftMultiply") EncryptionAlgorithm algorithm,
                                              @Qualifier("randomKeyGenerator")KeyGenerator keyGenerator,
                                              FileIOHandler fileIOHandler, UIManager uiManager, EncryptorParameters parameters,
                                              @Qualifier("observersList") List<Observer> observers) {
        return new ShiftEncryptor(algorithm, keyGenerator, fileIOHandler, uiManager, parameters, observers);
    }

    @Autowired
    @Bean("repeatEnc")
    public FileEncryptor createRepeatEncryptor(@Qualifier("shiftMultiply") EncryptionAlgorithm algorithm,
                                               @Qualifier("randomKeyGenerator") KeyGenerator keyGenerator,
                                               FileIOHandler fileIOHandler, UIManager uiManager, EncryptorParameters parameters,
                                               @Qualifier("observersList") List<Observer> observers) {
        return new RepeatEncryptor(algorithm, keyGenerator, fileIOHandler, uiManager, 10, parameters, observers);
    }
}
