package encryptor.algorithms;

import org.springframework.stereotype.Service;

@Service
public interface EncryptionAlgorithm {
    String encrypt(String message, int key, String separator);
    String decrypt(String cipher, int key, String separator);
}
