package algorithms.shift;

import algorithms.EncryptionAlgorithm;

import javax.swing.*;
import java.math.BigInteger;
import java.util.Properties;
import java.util.function.BinaryOperator;

public abstract class ShiftAlgorithm implements EncryptionAlgorithm {
    private Properties properties;

    public ShiftAlgorithm(Properties properties) {
        this.properties = properties;
    }

    String shiftEncrypt(String text, int key, BinaryOperator<BigInteger> operator) {
        StringBuilder message = new StringBuilder();
        char[] textArr = text.toCharArray();
        for(char letter : textArr) {
            BigInteger res = operator.apply(BigInteger.valueOf((int)letter), BigInteger.valueOf(key));
            message.append(res).append(properties.getProperty("encryptionSplittingChar"));
        }
        return message.toString();
    }


    String shiftDecrypt(String text, int key, BinaryOperator<BigInteger> operator) {
        StringBuilder message = new StringBuilder();
        String[] textArr = text.split(properties.getProperty("encryptionSplittingChar"));
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
