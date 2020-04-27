package encryptors;

import algorithms.EncryptionAlgorithm;
import generators.KeyGenerator;
import managers.FileIOHandler;
import managers.JAXBManager;
import pojos.EncryptorParameters;

public class RepeatEncryptor extends FileEncryptor {
    private int repeats;

    public RepeatEncryptor(EncryptionAlgorithm algorithm, KeyGenerator keyGenerator, FileIOHandler fileIOHandler, int repeats, EncryptorParameters parameters) {
        super(algorithm, keyGenerator, fileIOHandler, parameters);
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
    public void generateKeys() {
        this.keys = keyGenerator.generateKeys(repeats);
    }
}
