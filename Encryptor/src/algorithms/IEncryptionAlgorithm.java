package algorithms;

public interface IEncryptionAlgorithm {
    String encrypt(String message, int key);
    String decrypt(String cipher, int key);
}
