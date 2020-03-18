package algorithms;

import java.math.BigInteger;
import java.util.function.BinaryOperator;

public abstract class ShiftAlgorithm implements IEncryptionAlgorithm {

    public String shiftEncrypt(String text, int key, BinaryOperator<BigInteger> operator) {
        StringBuilder message = new StringBuilder();
        char[] textArr = text.toCharArray();
        for(char letter : textArr) {
            BigInteger res = operator.apply(BigInteger.valueOf((int)letter), BigInteger.valueOf(key));
            message.append(res).append(' ');
        }
        return message.toString();
    }


    public String shiftDecrypt(String text, int key, BinaryOperator<BigInteger> operator) {
        StringBuilder message = new StringBuilder();
        String[] textArr = text.split(" ");
        for(String letter : textArr) {
            message.append((char) operator.apply(new BigInteger(letter), BigInteger.valueOf(key)).intValue());
        }
        return message.toString();
    }

    @Override
    public abstract String encrypt(String message, int key);

    @Override
    public abstract String decrypt(String cipher, int key);
}
