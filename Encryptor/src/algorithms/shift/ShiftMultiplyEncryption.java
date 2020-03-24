package algorithms.shift;

import java.math.BigInteger;
import java.util.Properties;

public class ShiftMultiplyEncryption extends ShiftAlgorithm {
    public ShiftMultiplyEncryption(Properties properties) {
        super(properties);
    }

    @Override
    public String encrypt(String message, int key) {
        return shiftEncrypt(message, key, BigInteger::multiply);
    }

    @Override
    public String decrypt(String cipher, int key) {
        return shiftDecrypt(cipher, key, BigInteger::divide);
    }
}
