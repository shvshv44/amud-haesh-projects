package algorithms.shift;

import algorithms.EncryptionAlgorithm;

import java.math.BigInteger;
import java.util.function.BinaryOperator;

public abstract class ShiftAlgorithm implements EncryptionAlgorithm {

    public ShiftAlgorithm() {
    }

    String shiftEncrypt(String text, int key, String separator, BinaryOperator<BigInteger> operator) {
        StringBuilder message = new StringBuilder();
        String[] textArray = text.split(separator);
        for(String letter : textArray) {
            BigInteger res = operator.apply(BigInteger.valueOf(Integer.valueOf(letter)), BigInteger.valueOf(key));
            message.append(res).append(separator);
        }
        return message.toString();
    }


    String shiftDecrypt(String text, int key, String separator, BinaryOperator<BigInteger> operator) {
        StringBuilder message = new StringBuilder();
        String[] textArray = text.split(separator);
        for(String letter : textArray) {
            message.append((char) operator.apply(new BigInteger(letter), BigInteger.valueOf(key)).intValue());
        }
        return message.toString();
    }
}
