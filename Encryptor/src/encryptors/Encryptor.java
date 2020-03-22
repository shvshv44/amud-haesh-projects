package encryptors;

import exceptions.DecryptionNotExistException;

public interface Encryptor {
    String encrypt(String message, int key);
    String decrypt(String cipher, int key) throws DecryptionNotExistException;
}
