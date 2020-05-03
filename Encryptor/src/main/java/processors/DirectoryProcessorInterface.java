package processors;

import java.io.File;

public interface DirectoryProcessorInterface {
    int[] generateKeys();
    void encryptFile(File file);
    void decryptFile(File file, String keyPath);
    void encryptDirectory(File[] files);
    void decryptDirectory(File[] files, String keyPath);
}
