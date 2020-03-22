package encryptors;

import exceptions.DecryptionNotExistException;

public interface IEncryptor {
    String encrypt(String message, int key);
    String decrypt(String cipher, int key) throws DecryptionNotExistException;
}
