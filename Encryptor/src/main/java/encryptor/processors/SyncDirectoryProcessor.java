package encryptor.processors;

import encryptor.encryptors.FileEncryptor;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SyncDirectoryProcessor extends DirectoryProcessor {

    public SyncDirectoryProcessor(FileEncryptor fileEncryptor) {
        super(fileEncryptor);
    }

    @Override
    public void encryptFile(File file, String keyPath) {
        fileEncryptor.startEncryption(file, keyPath);
    }

    @Override
    public void decryptFile(File file, String keyPath) {
        fileEncryptor.startDecryption(file, keyPath);
    }
}
