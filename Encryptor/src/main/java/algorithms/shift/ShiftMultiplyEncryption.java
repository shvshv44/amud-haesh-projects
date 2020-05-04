package algorithms.shift;

import java.math.BigInteger;

public class ShiftMultiplyEncryption extends ShiftAlgorithm {

    @Override
    public String encrypt(String message, int key, String separator) {
        return operateShift(message, key, separator, BigInteger::multiply);
    }

    @Override
    public String decrypt(String cipher, int key, String separator) {
        return operateShift(cipher, key, separator, BigInteger::divide);
    }
}
