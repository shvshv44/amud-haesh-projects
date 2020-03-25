package encryptors;

import algorithms.EncryptionAlgorithm;
import exceptions.DecryptionNotExistException;

import java.util.StringJoiner;

public abstract class Encryptor {
    protected String separator;
    protected EncryptionAlgorithm algorithm;

    public Encryptor(EncryptionAlgorithm algorithm, String separator) {
        this.algorithm = algorithm;
        this.separator = separator;
    }

    abstract String encrypt(String message, int key);
    abstract String decrypt(String cipher, int key) throws DecryptionNotExistException;

    protected String prepareMessageForEncryption(String message) {
        char[] charsArray = message.toCharArray();
        StringJoiner formattedString = new StringJoiner(separator);
        for(char letter : charsArray) {
            int letterValue = (int)letter;
            formattedString.add(String.valueOf(letterValue));
        }
        return formattedString.toString();
    }
}
