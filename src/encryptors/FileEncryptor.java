package encryptors;

import exceptions.DecryptionNotExistException;
import generators.KeyGenerator;
import managers.FileManager;

import java.io.IOException;
import java.nio.file.Paths;

public class FileEncryptor {
    private IEncryptor encryptor;
    private KeyGenerator keyGenerator;
    private FileManager fileManager;

    public FileEncryptor(IEncryptor encryptor, KeyGenerator keyGenerator, FileManager fileManager) {
        this.encryptor = encryptor;
        this.keyGenerator = keyGenerator;
        this.fileManager = fileManager;
    }

    public void encrypt(String pathToFile) {
        try {
            tryToEncrypt(pathToFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Cannot split the file path. Check the path.");
        }
    }

    public void decrypt(String pathToFile, String pathToKey) {
        try {
            tryToDecrypt(pathToFile, pathToKey);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Cannot split the file path. Check the path.");
        } catch (DecryptionNotExistException e) {
            System.err.println("decryption not found.");
        }
    }

    private void tryToEncrypt(String pathToFile) throws IOException {
        int key = keyGenerator.generateKey();
        String cipherPath = pathToFile.split("\\.")[0] + "_encrypted." + pathToFile.split("\\.")[1];
        String keyPath = Paths.get(pathToFile).getParent() + "\\key.txt";
        String message = fileManager.readFile(pathToFile);
        String cipher = encryptor.encrypt(message, key);
        publishEncryptionResults(cipherPath, cipher, keyPath, key);
    }

    private void tryToDecrypt(String pathToFile, String pathToKey) throws IOException, DecryptionNotExistException {
        int key = Integer.valueOf(fileManager.readFile(pathToKey));
        String messagePath = pathToFile.split("\\.")[0] + "_decrypted." + pathToFile.split("\\.")[1];
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
