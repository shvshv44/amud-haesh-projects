package algorithms;

public interface EncryptionAlgorithm {
    String encrypt(String message, int key, String separator);
    String decrypt(String cipher, int key, String separator);
}
