package encryptors;

import algorithms.EncryptionAlgorithm;
import generators.KeyGenerator;
import managers.FileManager;
import pojos.EncryptorParameters;

import java.util.Properties;

public class ShiftEncryptor extends Encryptor {

    public ShiftEncryptor(EncryptionAlgorithm algorithm, KeyGenerator keyGenerator, FileManager fileManager, EncryptorParameters parameters) {
        super(algorithm, keyGenerator, fileManager, parameters);
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
