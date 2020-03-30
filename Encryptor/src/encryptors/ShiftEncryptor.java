package encryptors;

import algorithms.EncryptionAlgorithm;
import generators.KeyGenerator;
import managers.FileManager;

import java.util.Properties;

public class ShiftEncryptor extends Encryptor {

    public ShiftEncryptor(EncryptionAlgorithm algorithm, KeyGenerator keyGenerator, FileManager fileManager,
                          String separator, String pathSplitChar, String encryptionEnding, String decryptionEnding, String keyFileName) {
        super(algorithm, keyGenerator, fileManager, separator, pathSplitChar, encryptionEnding, decryptionEnding, keyFileName);
    }

    @Override
    public String encrypt(String message) {
        int key = getKey();
        return algorithm.encrypt(message, key, SEPARATOR);
    }

    @Override
    public String decrypt(String cipher) {
        int key = getKey();
        return algorithm.decrypt(cipher, key, SEPARATOR);
    }

    private int getKey() {
        return keys[0]; // the only key is in the first place
    }
}
