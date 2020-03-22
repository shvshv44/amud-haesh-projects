package encryptors;

import algorithms.IEncryptionAlgorithm;
import exceptions.DecryptionNotExistException;
import generators.KeyGenerator;

public class RepeatEncryptor implements IEncryptor {

    private int repeats;
    private IEncryptionAlgorithm algorithm;
    private KeyGenerator keyGenerator;

    public RepeatEncryptor(IEncryptionAlgorithm algorithm, int n, KeyGenerator generator) {
        this.algorithm = algorithm;
        this.repeats = n;
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
        System.err.println("cannot decrypt repeat encryption.");
        throw new DecryptionNotExistException();
    }
}
