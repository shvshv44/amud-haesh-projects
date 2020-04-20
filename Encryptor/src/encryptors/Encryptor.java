package encryptors;

import algorithms.EncryptionAlgorithm;
import generators.KeyGenerator;
import managers.FileManager;
import pojos.EncryptorParameters;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.StringJoiner;

public abstract class Encryptor {
    private FileManager fileManager;
    protected KeyGenerator keyGenerator;
    protected EncryptionAlgorithm algorithm;
    protected int[] keys;

    protected EncryptorParameters parameters;

    public Encryptor(EncryptionAlgorithm algorithm, KeyGenerator keyGenerator, FileManager fileManager, EncryptorParameters parameters) {
        this.keyGenerator = keyGenerator;
        this.fileManager = fileManager;
        this.algorithm = algorithm;
        this.keys = new int[1]; // the default number of keys is 1

        this.parameters = parameters;
    }

    abstract String encrypt(String message);
    abstract String decrypt(String cipher);

    public void startEncryption(String pathToFile) {
        try {
            tryToEncrypt(pathToFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException | InvalidPathException e) {
            System.err.println("Cannot split the file path. Check the path.");
        }
    }

    public void startDecryption(String pathToFile, String pathToKey) {
        try {
            tryToDecrypt(pathToFile, pathToKey);
        } catch (IOException e) {
            System.err.println("Error while decrypting: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException | InvalidPathException e) {
            System.err.println("Cannot split the file path. Check the path.");
        } catch (NumberFormatException e) {
            String foundKey = e.getMessage().split(": ")[1];
            System.err.println("Error while decrypting: wrong key format. The key found was " + foundKey);
        }
    }

    private void tryToEncrypt(String pathToFile) throws IOException {
        generateKeys();
        String[] splitPath = pathToFile.split(parameters.getPathSeparator());
        String cipherPath = splitPath[0] + parameters.getEncryptedEnding() + splitPath[1];
        String keyPath = Paths.get(pathToFile).getParent() + parameters.getKeyFileName();
        String message = fileManager.readFile(pathToFile);
        String readyToEncryptMessage = prepareMessageForEncryption(message);
        String cipher = encrypt(readyToEncryptMessage);
        publishEncryptionResults(cipherPath, cipher, keyPath);
    }

    private void tryToDecrypt(String pathToFile, String pathToKey) throws IOException, NumberFormatException {
        readKeys(fileManager.readFile(pathToKey));
        String[] splitPath = pathToFile.split(parameters.getPathSeparator());
        String messagePath = splitPath[0] + parameters.getDecryptedEnding()+ splitPath[1];
        String cipher = fileManager.readFile(pathToFile);
        String message = decrypt(cipher);
        String convertedMessage = convertDecryptionToText(message);
        publishDecryptionResults(messagePath, convertedMessage);
    }

    private String prepareMessageForEncryption(String message) {
        char[] charsArray = message.toCharArray();
        StringJoiner formattedString = new StringJoiner(parameters.getSeparator());
        for(char letter : charsArray) {
            int letterValue = (int)letter;
            formattedString.add(String.valueOf(letterValue));
        }
        return formattedString.toString();
    }

    private String convertDecryptionToText(String message) {
        String[] decryptedArray = message.split(parameters.getSeparator());
        StringBuilder messageText = new StringBuilder();
        for(String decryptedLetter : decryptedArray) {
            messageText.append((char)(Integer.parseInt(decryptedLetter)));
        }
        return messageText.toString();
    }

    private void publishEncryptionResults(String cipherPath, String cipher, String keyPath) throws IOException {
        fileManager.writeToFile(cipherPath, cipher);
        fileManager.writeToFile(keyPath, getKeysString());
        System.out.println("The encrypted file is in: " + cipherPath);
        System.out.println("The key is in: " + keyPath);
    }

    private void publishDecryptionResults(String messagePath, String message) throws IOException {
        fileManager.writeToFile(messagePath, message);
        System.out.println("The decrypted file is in: " + messagePath);
    }

    private String getKeysString() {
        StringJoiner formattedString = new StringJoiner(parameters.getSeparator());
        for(int key : keys) {
            formattedString.add(String.valueOf(key));
        }
        return formattedString.toString();
    }

    private void readKeys(String keysString) {
        String[] keysArray = keysString.split(parameters.getSeparator());
        for (int i = 0; i < keysArray.length; i++) {
            keys[i] = Integer.parseInt(keysArray[i]);
        }
    }

    protected void generateKeys() {
        this.keys = keyGenerator.generateKeys();
    }
}
