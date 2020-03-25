package encryptors;

import algorithms.shift.ShiftAlgorithm;
import exceptions.DecryptionNotExistException;

public class ShiftEncryptor extends Encryptor {

    public ShiftEncryptor(ShiftAlgorithm shiftAlgorithm, String separator) {
        super(shiftAlgorithm, separator);
    }

    @Override
    public String encrypt(String message, int key) {
        String formattedMessage = prepareMessageForEncryption(message);
        return algorithm.encrypt(formattedMessage, key, separator);
    }

    @Override
    public String decrypt(String cipher, int key) throws DecryptionNotExistException {
        return algorithm.decrypt(cipher, key, separator);

    }


}
