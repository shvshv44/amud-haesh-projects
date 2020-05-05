package encryptor.algorithms.shift;

import encryptor.algorithms.EncryptionAlgorithm;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.function.BinaryOperator;

@Service
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
