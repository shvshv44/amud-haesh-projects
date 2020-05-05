package encryptor.processors;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public interface DirectoryProcessorInterface {
    int[] generateKeys();
    void encryptFile(File file);
    void decryptFile(File file, String keyPath);
    void encryptDirectory(File[] files);
    void decryptDirectory(File[] files, String keyPath);
}
