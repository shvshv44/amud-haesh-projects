package encryptors;

import algorithms.EncryptionAlgorithm;
import generators.KeyGenerator;
import listeners.Observable;
import managers.FileIOHandler;
import pojos.*;
import ui.UIManager;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Calendar;
import java.util.StringJoiner;

public abstract class FileEncryptor extends Observable {
    private FileIOHandler fileIOHandler;
    protected KeyGenerator keyGenerator;
    protected EncryptionAlgorithm algorithm;
    private UIManager uiManager;
    protected int[] keys;
    protected EncryptorParameters parameters;

    public FileEncryptor(EncryptionAlgorithm algorithm, KeyGenerator keyGenerator, FileIOHandler fileIOHandler, UIManager uiManager, EncryptorParameters parameters) {
        super();
        this.keyGenerator = keyGenerator;
        this.fileIOHandler = fileIOHandler;
        this.algorithm = algorithm;
        this.uiManager = uiManager;
        this.keys = new int[1]; // the default number of keys is 1

        this.parameters = parameters;
    }


    abstract String encrypt(String message);
    abstract String decrypt(String cipher);

    public void startEncryption(File pathToFile) {
        try {
            tryToEncrypt(pathToFile);
        } catch (IOException e) {
            uiManager.printError(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException | InvalidPathException e) {
            uiManager.printError("Cannot split the file path. Check the path.");
        } catch (JAXBException e) {
            e.printStackTrace();
            uiManager.printError("Cannot parse the encryption result to xml.");
        }
    }

    public void startDecryption(File pathToFile, String pathToKey) {
        try {
            tryToDecrypt(pathToFile, pathToKey);
        } catch (IOException e) {
            uiManager.printError("Error while decrypting: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException | InvalidPathException e) {
            uiManager.printError("Cannot split the file path. Check the path.");
        } catch (NumberFormatException e) {
            String foundKey = e.getMessage().split(": ")[1];
            uiManager.printError("Error while decrypting: wrong key format. The key found was " + foundKey);
        } catch (JAXBException e) {
            e.printStackTrace();
            uiManager.printError("Cannot parse the encryption result to xml.");
        }
    }

    private void tryToEncrypt(File file) throws IOException, JAXBException {
        readKeys(fileIOHandler.readFile(file.getParent() + parameters.getEncryptedEnding() +parameters.getKeyFileName()));
        String cipherPath = file.getParent() + parameters.getEncryptedEnding() + file.getName();
        String message = fileIOHandler.readFile(file.getPath());
        String readyToEncryptMessage = prepareMessageForEncryption(message);
        encryptionStarted(file.getName());
        long startTime = Calendar.getInstance().getTimeInMillis();
        String cipher = encrypt(readyToEncryptMessage);
        long totalTime = Calendar.getInstance().getTimeInMillis() - startTime;
        EncryptionLogEventArgs args = new EncryptionArgs(file.getPath(), cipherPath, algorithm.getClass().toString(), totalTime);
        encryptionEnded(args);
        writeEncryptionResults(cipherPath, cipher);
        EncryptionResults.getInstance().getLogList().add(args);
    }

    private void tryToDecrypt(File file, String pathToKey) throws IOException, NumberFormatException, JAXBException {
        readKeys(fileIOHandler.readFile(pathToKey));
        String messagePath = file.getParent() + parameters.getDecryptedEnding() + file.getName();
        String cipher = fileIOHandler.readFile(file.getPath());
        decryptionStarted(file.getName());
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

    private void writeEncryptionResults(String cipherPath, String cipher) throws IOException {
        fileIOHandler.writeToFile(cipherPath, cipher);
    }

    private void writeDecryptionResults(String messagePath, String message) throws IOException {
        fileIOHandler.writeToFile(messagePath, message);
    }


    private void readKeys(String keysString) {
        String[] keysArray = keysString.split(parameters.getSeparator());
        for (int i = 0; i < keysArray.length; i++) {
            keys[i] = Integer.parseInt(keysArray[i]);
        }
    }

    public int[] generateKeys() {
        return keyGenerator.generateKeys();
    }
}
