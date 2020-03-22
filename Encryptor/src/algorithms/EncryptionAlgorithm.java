package algorithms;

public interface EncryptionAlgorithm {
    String encrypt(String message, int key);
    String decrypt(String cipher, int key);
}
