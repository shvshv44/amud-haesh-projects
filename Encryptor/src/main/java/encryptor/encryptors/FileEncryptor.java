package encryptor.encryptors;

import encryptor.algorithms.EncryptionAlgorithm;
import encryptor.generators.KeyGenerator;
import encryptor.listeners.Observable;
import encryptor.listeners.Observer;
import encryptor.managers.FileIOHandler;
import encryptor.pojos.DecryptionArgs;
import encryptor.pojos.EncryptionArgs;
import encryptor.pojos.EncryptionResults;
import encryptor.pojos.EncryptorParameters;
import encryptor.ui.UIManager;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Calendar;
import java.util.List;
import java.util.StringJoiner;

@Service
public abstract class FileEncryptor extends Observable {
    private FileIOHandler fileIOHandler;
    protected KeyGenerator keyGenerator;
    protected EncryptionAlgorithm algorithm;
    private UIManager uiManager;
    protected int[] keys;
    protected EncryptorParameters parameters;

    public FileEncryptor(EncryptionAlgorithm algorithm, KeyGenerator keyGenerator, FileIOHandler fileIOHandler, UIManager uiManager, EncryptorParameters parameters, List<Observer> observers) {
        super(observers);
        this.keyGenerator = keyGenerator;
        this.fileIOHandler = fileIOHandler;
        this.algorithm = algorithm;
        this.uiManager = uiManager;
        this.keys = new int[1]; // the default number of keys is 1
        this.parameters = parameters;
    }

    abstract String encrypt(String message);
    abstract String decrypt(String cipher);

    public void startEncryption(File pathToFile, String keyPath) {
        try {
            tryToEncrypt(pathToFile, keyPath);
        } catch (IOException e) {
            uiManager.printError(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException | InvalidPathException e) {
            uiManager.printError("Cannot split the file path. Check the path.");
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
        }
    }

    public int[] generateKeys() {
        return keyGenerator.generateKeys();
    }

    private void tryToEncrypt(File file, String keyPath) throws IOException {
        readKeys(fileIOHandler.readFile(keyPath));
        String cipherPath = file.getParent() + parameters.getEncryptedFolderName() + file.getName();
        String message = fileIOHandler.readFile(file.getPath());
        String formattedMessage = prepareMessageForEncryption(message);
        encryptionStarted(file.getName());
        long startTime = Calendar.getInstance().getTimeInMillis();
        String cipher = encrypt(formattedMessage);
        long totalTime = Calendar.getInstance().getTimeInMillis() - startTime;
        fileIOHandler.writeToFile(cipherPath, cipher);
        handleEncryptionResult(new EncryptionArgs(file.getPath(), cipherPath, algorithm.getClass().getSimpleName(), totalTime));
    }

    private void tryToDecrypt(File file, String pathToKey) throws IOException, NumberFormatException {
        readKeys(fileIOHandler.readFile(pathToKey));
        String messagePath = file.getParent() + parameters.getDecryptedFolderName() + file.getName();
        String cipher = fileIOHandler.readFile(file.getPath());
        decryptionStarted(file.getName());
        long startTime = Calendar.getInstance().getTimeInMillis();
        String message = decrypt(cipher);
        long totalTime = Calendar.getInstance().getTimeInMillis() - startTime;
        String convertedMessage = convertDecryptionToText(message);
        fileIOHandler.writeToFile(messagePath, convertedMessage);
        handleDecryptionResult(new DecryptionArgs(file.getPath(), messagePath, algorithm.getClass().getSimpleName(), totalTime));
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

    private void readKeys(String keysString) {
        String[] keysArray = keysString.split(parameters.getSeparator());
        for (int i = 0; i < keysArray.length; i++) {
            keys[i] = Integer.parseInt(keysArray[i]);
        }
    }

    private void handleEncryptionResult(EncryptionArgs args) {
        encryptionEnded(args);
        EncryptionResults.getInstance().getLogList().add(args);
    }

    private void handleDecryptionResult(DecryptionArgs args) {
        decryptionEnded(args);
        EncryptionResults.getInstance().getLogList().add(args);
    }
}