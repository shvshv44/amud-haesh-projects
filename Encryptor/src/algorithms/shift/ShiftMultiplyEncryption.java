package algorithms.shift;

import java.math.BigInteger;

public class ShiftMultiplyEncryption extends ShiftAlgorithm {
    @Override
    public String encrypt(String message, int key) {
        return shiftEncrypt(message, key, BigInteger::multiply);
    }

    @Override
    public String decrypt(String cipher, int key) {
        return shiftDecrypt(cipher, key, BigInteger::divide);
    }
}
