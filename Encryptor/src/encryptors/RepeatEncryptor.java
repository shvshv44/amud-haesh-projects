package encryptors;

import algorithms.EncryptionAlgorithm;
import exceptions.DecryptionNotExistException;
import generators.KeyGenerator;

public class RepeatEncryptor implements Encryptor {

    private int repeats;
    private EncryptionAlgorithm algorithm;
    private KeyGenerator keyGenerator;

    public RepeatEncryptor(EncryptionAlgorithm algorithm, int repeats, KeyGenerator generator) {
        this.algorithm = algorithm;
        this.repeats = repeats;
        this.keyGenerator = generator;
    }

    @Override
    public String encrypt(String message, int key) {
        for (int i = 0; i < repeats; i++) {
            key = keyGenerator.generateKey();
            message = algorithm.encrypt(message, key);
        }
        return message;
    }

    @Override
    public String decrypt(String cipher, int key) throws DecryptionNotExistException {
        throw new DecryptionNotExistException("cannot decrypt repeat encryption.");
    }
}
