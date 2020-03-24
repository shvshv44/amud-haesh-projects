package algorithms.shift;

import java.math.BigInteger;
import java.util.Properties;

public class ShiftUpEncryption extends ShiftAlgorithm {

    public ShiftUpEncryption(Properties properties) {
        super(properties);
    }

    @Override
    public String encrypt(String message, int key) {
        return shiftEncrypt(message, key, BigInteger::add);
    }

    @Override
    public String decrypt(String cipher, int key) {
        return shiftDecrypt(cipher, key, BigInteger::subtract);
    }
}
