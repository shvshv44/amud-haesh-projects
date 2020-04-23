package encryptors;

import algorithms.EncryptionAlgorithm;
import generators.KeyGenerator;
import listeners.Observable;
import managers.FileIOHandler;
import managers.JAXBManager;
import pojos.DecryptionArgs;
import pojos.EncryptionArgs;
import pojos.EncryptionLogEventArgs;
import pojos.EncryptorParameters;

import javax.xml.bind.JAXBException;
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
    private JAXBManager jaxbManager;

    public FileEncryptor(JAXBManager jaxbManager, EncryptionAlgorithm algorithm, KeyGenerator keyGenerator, FileIOHandler fileIOHandler, EncryptorParameters parameters) {
        super();
        this.jaxbManager = jaxbManager;
        this.keyGenerator = keyGenerator;
        this.fileIOHandler = fileIOHandler;
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
        } catch (JAXBException e) {
            e.printStackTrace();
            System.err.println("Cannot parse the encryption result to xml.");
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
        } catch (JAXBException e) {
            e.printStackTrace();
            System.err.println("Cannot parse the encryption result to xml.");
        }
    }

    private void tryToEncrypt(String pathToFile) throws IOException, JAXBException {
        generateKeys();
        String[] splitPath = pathToFile.split(parameters.getPathSeparator());
        String cipherPath = splitPath[0] + parameters.getEncryptedEnding() + splitPath[1];
        String keyPath = Paths.get(pathToFile).getParent() + parameters.getKeyFileName();
        String message = fileIOHandler.readFile(pathToFile);
        String readyToEncryptMessage = prepareMessageForEncryption(message);
        encryptionStarted();
        long startTime = Calendar.getInstance().getTimeInMillis();
        String cipher = encrypt(readyToEncryptMessage);
        long totalTime = Calendar.getInstance().getTimeInMillis() - startTime;
        EncryptionLogEventArgs args = new EncryptionArgs(pathToFile, cipherPath, algorithm.getClass().toString(), totalTime);
        encryptionEnded(args);
        writeEncryptionResults(cipherPath, cipher, keyPath);
        jaxbManager.marshal(args);
    }

    private void tryToDecrypt(String pathToFile, String pathToKey) throws IOException, NumberFormatException, JAXBException {
        readKeys(fileIOHandler.readFile(pathToKey));
        String[] splitPath = pathToFile.split(parameters.getPathSeparator());
        String messagePath = splitPath[0] + parameters.getDecryptedEnding()+ splitPath[1];
        String cipher = fileIOHandler.readFile(pathToFile);
        decryptionStarted();
        long startTime = Calendar.getInstance().getTimeInMillis();
        String message = decrypt(cipher);
        long totalTime = Calendar.getInstance().getTimeInMillis() - startTime;
        String convertedMessage = convertDecryptionToText(message);
        EncryptionLogEventArgs args = new DecryptionArgs(pathToFile, messagePath, algorithm.getClass().toString(), totalTime);
        decryptionEnded(args);
        writeDecryptionResults(messagePath, convertedMessage);
        jaxbManager.marshal(args);
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

    protected void generateKeys() {
        this.keys = keyGenerator.generateKeys();
    }
}
