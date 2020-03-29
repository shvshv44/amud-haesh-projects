package encryptors;

import algorithms.EncryptionAlgorithm;
import generators.KeyGenerator;
import managers.FileManager;

import java.util.Properties;

public class RepeatEncryptor extends Encryptor {
    private int repeats;

    public RepeatEncryptor(EncryptionAlgorithm algorithm, KeyGenerator keyGenerator, FileManager fileManager, Properties properties, int repeats) {
        super(algorithm, keyGenerator, fileManager, properties);
        this.repeats = repeats;
        this.keys = new int[repeats];
    }

    @Override
    public String encrypt(String message) {
        for (int i = 0; i < repeats; i++) {
            int key = keys[i];
            message = algorithm.encrypt(message, key, SEPARATOR);
        }
        return message;
    }

    @Override
    public String decrypt(String cipher) {
        for (int i = repeats-1; i >= 0; i--) {
            cipher = algorithm.decrypt(cipher, keys[i], SEPARATOR);
        }
        return cipher;
    }

    @Override
    protected void generateKeys() {
        this.keys = keyGenerator.generateKeys(repeats);
    }
}
