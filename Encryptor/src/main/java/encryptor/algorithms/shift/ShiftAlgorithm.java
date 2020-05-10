package encryptor.algorithms.shift;

import encryptor.algorithms.EncryptionAlgorithm;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.StringJoiner;
import java.util.function.BinaryOperator;

@Service
public abstract class ShiftAlgorithm implements EncryptionAlgorithm {

    String operateShift(String text, int key, String separator, BinaryOperator<BigInteger> operator) {
        StringJoiner message = new StringJoiner(separator);
        String[] textArray = text.split(separator);
        for(String letter : textArray) {
            BigInteger res = operator.apply(new BigInteger(letter), BigInteger.valueOf(key));
            message.add(res.toString());
        }
        return message.toString();
    }
}
