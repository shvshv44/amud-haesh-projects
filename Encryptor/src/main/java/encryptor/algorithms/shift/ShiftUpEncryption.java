package encryptor.algorithms.shift;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@Qualifier
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
