package encryptor.encryptors;

import encryptor.algorithms.EncryptionAlgorithm;
import encryptor.generators.KeyGenerator;
import encryptor.listeners.EncryptionObserver;
import encryptor.managers.FileIOHandler;
import encryptor.pojos.EncryptorParameters;
import encryptor.ui.UIManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepeatEncryptor extends FileEncryptor {
    private int repeats;

    @Autowired
    public RepeatEncryptor(EncryptionAlgorithm algorithm,
                           KeyGenerator keyGenerator, FileIOHandler fileIOHandler, UIManager uiManager,
                           @Value("10") int repeats, EncryptorParameters parameters,
                           @Qualifier("observersList") List<EncryptionObserver> observers) {
        super(algorithm, keyGenerator, fileIOHandler, uiManager, parameters, observers);
        this.repeats = repeats;
        this.keys = new int[repeats];
    }

    @Override
    public String encrypt(String message) {
        for (int i = 0; i < repeats; i++) {
            int key = keys[i];
            message = algorithm.encrypt(message, key, parameters.getSeparator());
        }
        return message;
    }

    @Override
    public String decrypt(String cipher) {
        for (int i = repeats-1; i >= 0; i--) {
            cipher = algorithm.decrypt(cipher, keys[i], parameters.getSeparator());
        }
        return cipher;
    }

    @Override
    public int[] generateKeys() {
        return keyGenerator.generateKeys(repeats);
    }
}
