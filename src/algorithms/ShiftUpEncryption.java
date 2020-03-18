package algorithms;

import java.math.BigInteger;

public class ShiftUpEncryption extends ShiftAlgorithm {

    public ShiftUpEncryption() { }

    @Override
    public String encrypt(String message, int key) {
        return shiftEncrypt(message, key, BigInteger::add);
    }

    @Override
    public String decrypt(String cipher, int key) {
        return shiftDecrypt(cipher, key, BigInteger::subtract);
    }
}
