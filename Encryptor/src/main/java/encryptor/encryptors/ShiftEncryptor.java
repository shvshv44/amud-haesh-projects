package encryptor.encryptors;

import encryptor.algorithms.EncryptionAlgorithm;
import encryptor.generators.KeyGenerator;
import encryptor.listeners.Observer;
import encryptor.managers.FileIOHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import encryptor.pojos.EncryptorParameters;
import encryptor.ui.UIManager;

import java.util.List;

@Service
public class ShiftEncryptor extends FileEncryptor {

    @Autowired
    public ShiftEncryptor(@Qualifier("shiftUp") EncryptionAlgorithm algorithm,
                          KeyGenerator keyGenerator, FileIOHandler fileIOHandler, UIManager uiManager, EncryptorParameters parameters,
                          @Qualifier("observersList") List<Observer> observers) {
        super(algorithm, keyGenerator, fileIOHandler, uiManager, parameters, observers);
    }

    @Override
    public String encrypt(String message) {
        int key = getKey();
        return algorithm.encrypt(message, key, parameters.getSeparator());
    }

    @Override
    public String decrypt(String cipher) {
        int key = getKey();
        return algorithm.decrypt(cipher, key, parameters.getSeparator());
    }

    private int getKey() {
        return keys[0]; // the only key is in the first place
    }
}