package processors;

import java.io.File;

public interface DirectoryProcessorInterface {
    void generateKeys();
    void encryptFile(File file);
    void decryptFile(File file, String keyPath);
}
