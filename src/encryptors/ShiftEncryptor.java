package encryptors;

import algorithms.shift.ShiftAlgorithm;
import exceptions.DecryptionNotExistException;

public class ShiftEncryptor implements IEncryptor {
    private ShiftAlgorithm shiftAlgorithm;
    public ShiftEncryptor(ShiftAlgorithm shiftAlgorithm) {
        this.shiftAlgorithm = shiftAlgorithm;
    }


    @Override
    public String encrypt(String message, int key) {
        return shiftAlgorithm.encrypt(message, key);
    }

    @Override
    public String decrypt(String cipher, int key) throws DecryptionNotExistException {
        return shiftAlgorithm.decrypt(cipher, key);
    }
}
