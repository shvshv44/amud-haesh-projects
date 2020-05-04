package algorithms.shift;

import algorithms.EncryptionAlgorithm;

import java.math.BigInteger;
import java.util.function.BinaryOperator;

public abstract class ShiftAlgorithm implements EncryptionAlgorithm {

    String operateShift(String text, int key, String separator, BinaryOperator<BigInteger> operator) {
        StringBuilder message = new StringBuilder();
        String[] textArray = text.split(separator);
        for(String letter : textArray) {
            BigInteger res = operator.apply(new BigInteger(letter), BigInteger.valueOf(key));
            message.append(res).append(separator);
        }
        message.setLength(message.length() - separator.length()); // remove last separator
        return message.toString();
    }
}
