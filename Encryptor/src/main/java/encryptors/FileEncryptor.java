package encryptors;

import algorithms.EncryptionAlgorithm;
import generators.KeyGenerator;
import listeners.Observable;
import managers.FileIOHandler;
import pojos.*;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.StringJoiner;

public abstract class FileEncryptor extends Observable {
    private FileIOHandler fileIOHandler;
    protected KeyGenerator keyGenerator;
    protected EncryptionAlgorithm algorithm;
    protected int[] keys;
    protected EncryptorParameters parameters;

    public FileEncryptor(EncryptionAlgorithm algorithm, KeyGenerator keyGenerator, FileIOHandler fileIOHandler, EncryptorParameters parameters) {
        super();
        this.keyGenerator = keyGenerator;
        this.fileIOHandler = fileIOHandler;
        this.algorithm = algorithm;
        this.keys = new int[1]; // the default number of keys is 1

        this.parameters = parameters;
    }


    abstract String encrypt(String message);
    abstract String decrypt(String cipher);

    public void startEncryption(File pathToFile) {
        try {
            tryToEncrypt(pathToFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException | InvalidPathException e) {
            System.err.println("Cannot split the file path. Check the path.");
        } catch (JAXBException e) {
            e.printStackTrace();
            System.err.println("Cannot parse the encryption result to xml.");
        }
    }

    public void startDecryption(File pathToFile, String pathToKey) {
        try {
            tryToDecrypt(pathToFile, pathToKey);
        } catch (IOException e) {
            System.err.println("Error while decrypting: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException | InvalidPathException e) {
            System.err.println("Cannot split the file path. Check the path.");
        } catch (NumberFormatException e) {
            String foundKey = e.getMessage().split(": ")[1];
            System.err.println("Error while decrypting: wrong key format. The key found was " + foundKey);
        } catch (JAXBException e) {
            e.printStackTrace();
            System.err.println("Cannot parse the encryption result to xml.");
        }
    }

    private void tryToEncrypt(File file) throws IOException, JAXBException {
        String cipherPath = file.getParent() + parameters.getEncryptedEnding() + file.getName();
        String keyPath = file.getParent() + parameters.getEncryptedEnding() + parameters.getKeyFileName();
        String message = fileIOHandler.readFile(file.getPath());
        String readyToEncryptMessage = prepareMessageForEncryption(message);
        encryptionStarted();
        long startTime = Calendar.getInstance().getTimeInMillis();
        String cipher = encrypt(readyToEncryptMessage);
        long totalTime = Calendar.getInstance().getTimeInMillis() - startTime;
        EncryptionLogEventArgs args = new EncryptionArgs(file.getPath(), cipherPath, algorithm.getClass().toString(), totalTime);
        encryptionEnded(args);
        writeEncryptionResults(cipherPath, cipher, keyPath);
        EncryptionResults.getInstance().getLogList().add(args);
    }

    private void tryToDecrypt(File file, String pathToKey) throws IOException, NumberFormatException, JAXBException {
        readKeys(fileIOHandler.readFile(pathToKey));
        String messagePath = file.getParent() + parameters.getDecryptedEnding() + file.getName();
        String cipher = fileIOHandler.readFile(file.getPath());
        decryptionStarted();
        long startTime = Calendar.getInstance().getTimeInMillis();
        String message = decrypt(cipher);
        long totalTime = Calendar.getInstance().getTimeInMillis() - startTime;
        String convertedMessage = convertDecryptionToText(message);
        EncryptionLogEventArgs args = new DecryptionArgs(file.getPath(), messagePath, algorithm.getClass().toString(), totalTime);
        decryptionEnded(args);
        writeDecryptionResults(messagePath, convertedMessage);
        EncryptionResults.getInstance().getLogList().add(args);
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

    private void writeEncryptionResults(String cipherPath, String cipher, String keyPath) throws IOException {
        fileIOHandler.writeToFile(cipherPath, cipher);
        fileIOHandler.writeToFile(keyPath, getKeysString());
    }

    private void writeDecryptionResults(String messagePath, String message) throws IOException {
        fileIOHandler.writeToFile(messagePath, message);
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

    public void generateKeys() {
        this.keys = keyGenerator.generateKeys();
    }
}
