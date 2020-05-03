package encryptors;

import algorithms.EncryptionAlgorithm;
import generators.KeyGenerator;
import managers.FileIOHandler;
import managers.JAXBManager;
import pojos.EncryptorParameters;
import ui.UIManager;

public class ShiftEncryptor extends FileEncryptor {

    public ShiftEncryptor(EncryptionAlgorithm algorithm, KeyGenerator keyGenerator, FileIOHandler fileIOHandler, UIManager uiManager, EncryptorParameters parameters) {
        super(algorithm, keyGenerator, fileIOHandler, uiManager, parameters);
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
