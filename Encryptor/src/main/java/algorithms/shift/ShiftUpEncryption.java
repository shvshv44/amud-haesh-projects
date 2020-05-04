package algorithms.shift;

import java.math.BigInteger;

public class ShiftUpEncryption extends ShiftAlgorithm {

    @Override
    public String encrypt(String message, int key, String separator) {
        return operateShift(message, key, separator, BigInteger::add);
    }

    @Override
    public String decrypt(String cipher, int key, String separator) {
        return operateShift(cipher, key, separator, BigInteger::subtract);
    }
}
