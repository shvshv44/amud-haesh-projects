package encryptors;

import exceptions.DecryptionNotExistException;
import generators.KeyGenerator;
import managers.FileManager;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Properties;

public class FileEncryptor {
    private Properties properties;
    private Encryptor encryptor;
    private KeyGenerator keyGenerator;
    private FileManager fileManager;

    public FileEncryptor(Encryptor encryptor, KeyGenerator keyGenerator, FileManager fileManager, Properties properties) {
        this.encryptor = encryptor;
        this.keyGenerator = keyGenerator;
        this.fileManager = fileManager;
        this.properties = properties;
    }

    public void encrypt(String pathToFile) {
        try {
            tryToEncrypt(pathToFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException | InvalidPathException e) {
            System.err.println("Cannot split the file path. Check the path.");
        }
    }

    public void decrypt(String pathToFile, String pathToKey) {
        try {
            tryToDecrypt(pathToFile, pathToKey);
        } catch (IOException | DecryptionNotExistException e) {
            System.err.println("Error while decrypting: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException | InvalidPathException e) {
            System.err.println("Cannot split the file path. Check the path.");
        } catch (NumberFormatException e) {
            String foundKey = e.getMessage().split(": ")[1];
            System.err.println("Error while decrypting: wrong key format. The key found was " + foundKey);
        }
    }

    private void tryToEncrypt(String pathToFile) throws IOException {
        int key = keyGenerator.generateKey();
        String[] splitPath = pathToFile.split(properties.getProperty("pathSplittingChar"));
        String cipherPath = splitPath[0] + properties.getProperty("encryptedFileEnding") + splitPath[1];
        String keyPath = Paths.get(pathToFile).getParent() + properties.getProperty("keyFileName");
        String message = fileManager.readFile(pathToFile);
        String cipher = encryptor.encrypt(message, key);
        publishEncryptionResults(cipherPath, cipher, keyPath, key);
    }

    private void tryToDecrypt(String pathToFile, String pathToKey) throws IOException, DecryptionNotExistException, NumberFormatException {
        int key = Integer.valueOf(fileManager.readFile(pathToKey));
        String[] splitPath = pathToFile.split(properties.getProperty("pathSplittingChar"));
        String messagePath = splitPath[0] + properties.getProperty("decryptedFileEnding") + splitPath[1];
        String cipher = fileManager.readFile(pathToFile);
        String message = encryptor.decrypt(cipher, key);
        publishDecryptionResults(messagePath, message);
    }

    private void publishEncryptionResults(String cipherPath, String cipher, String keyPath, int key) throws IOException {
        fileManager.writeToFile(cipherPath, cipher);
        fileManager.writeToFile(keyPath, String.valueOf(key));
        System.out.println("The encrypted file is in: " + cipherPath);
        System.out.println("The key is in: " + keyPath);
    }

    private void publishDecryptionResults(String messagePath, String message) throws IOException {
        fileManager.writeToFile(messagePath, message);
        System.out.println("The decrypted file is in: " + messagePath);
    }
}
