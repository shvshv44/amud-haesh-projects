package encryptors;

import algorithms.IEncryptionAlgorithm;
import generators.KeyGenerator;

public class RepeatEncryption {

    private int repeats;
    private IEncryptionAlgorithm algorithm;
    private KeyGenerator keyGenerator;

    public RepeatEncryption(IEncryptionAlgorithm algorithm, int n, KeyGenerator generator) {
        this.algorithm = algorithm;
        this.repeats = n;
        this.keyGenerator = generator;
    }

    public String encrypt(String message) {
        for (int i = 0; i < repeats; i++) {
            int key = keyGenerator.generateKey();
            message = algorithm.encrypt(message, key);
        }
        return message;
    }
}
