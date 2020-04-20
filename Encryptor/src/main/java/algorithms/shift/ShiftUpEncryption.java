package algorithms.shift;

import java.math.BigInteger;

public class ShiftUpEncryption extends ShiftAlgorithm {

    public ShiftUpEncryption() {
    }

    @Override
    public String encrypt(String message, int key, String separator) {
        return shiftEncrypt(message, key, separator, BigInteger::add);
    }

    @Override
    public String decrypt(String cipher, int key, String separator) {
        return shiftDecrypt(cipher, key, separator, BigInteger::subtract);
    }
}
