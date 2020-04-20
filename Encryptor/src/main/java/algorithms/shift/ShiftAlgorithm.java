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
            BigInteger res = operator.apply(new BigInteger(letter), BigInteger.valueOf(key));
            message.append(res).append(separator);
        }
        message.setLength(message.length() - 1); // remove last separator
        return message.toString();
    }


    String shiftDecrypt(String text, int key, String separator, BinaryOperator<BigInteger> operator) {
        StringBuilder message = new StringBuilder();
        String[] textArray = text.split(separator);
        for(String letter : textArray) {
            message.append(operator.apply(new BigInteger(letter), BigInteger.valueOf(key))).append(separator);
        }
        message.setLength(message.length() - 1); // remove last separator
        return message.toString();
    }
}
