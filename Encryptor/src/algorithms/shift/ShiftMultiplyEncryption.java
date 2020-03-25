package algorithms.shift;

import java.math.BigInteger;

public class ShiftMultiplyEncryption extends ShiftAlgorithm {
    public ShiftMultiplyEncryption() {
    }

    @Override
    public String encrypt(String message, int key, String separator) {
        return shiftEncrypt(message, key, separator, BigInteger::multiply);
    }

    @Override
    public String decrypt(String cipher, int key, String separator) {
        return shiftDecrypt(cipher, key, separator, BigInteger::divide);
    }
}
