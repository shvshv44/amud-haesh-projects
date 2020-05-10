package encryptor.processors;

import encryptor.encryptors.FileEncryptor;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class AsyncDirectoryProcessor extends DirectoryProcessor {

    public AsyncDirectoryProcessor(FileEncryptor fileEncryptor) {
        super(fileEncryptor);
    }

    @Override
    public void encryptFile(File file, String keyPath) {
        new Thread(() -> fileEncryptor.startEncryption(file, keyPath)).start();
    }

    @Override
    public void decryptFile(File file, String keyPath) {
        new Thread(() -> fileEncryptor.startDecryption(file, keyPath)).start();
    }
}
