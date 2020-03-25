package encryptors;

import algorithms.EncryptionAlgorithm;
import exceptions.DecryptionNotExistException;
import generators.KeyGenerator;

public class RepeatEncryptor extends Encryptor {

    private int repeats;
    private KeyGenerator keyGenerator;

    public RepeatEncryptor(EncryptionAlgorithm algorithm, int repeats, KeyGenerator generator, String separator) {
        super(algorithm, separator);
        this.repeats = repeats;
        this.keyGenerator = generator;
    }

    @Override
    public String encrypt(String message, int key) {
        String formattedMessage = prepareMessageForEncryption(message);
        for (int i = 0; i < repeats; i++) {
            message = algorithm.encrypt(formattedMessage, key, separator);
            key = keyGenerator.generateKey();
        }
        return message;
    }

    @Override
    public String decrypt(String cipher, int key) throws DecryptionNotExistException {
        return algorithm.decrypt(cipher, key, separator);
        //throw new DecryptionNotExistException("cannot decrypt repeat encryption.");
    }
}
