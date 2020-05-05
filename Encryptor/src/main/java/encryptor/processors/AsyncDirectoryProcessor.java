package encryptor.processors;

import encryptor.encryptors.FileEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class AsyncDirectoryProcessor extends DirectoryProcessor {

    @Autowired
    public AsyncDirectoryProcessor(@Qualifier("repeatEnc") FileEncryptor fileEncryptor) {
        super(fileEncryptor);
    }

    @Override
    public void encryptFile(File file) {
        new Thread(() -> fileEncryptor.startEncryption(file)).start();
    }

    @Override
    public void decryptFile(File file, String keyPath) {
        new Thread(() -> fileEncryptor.startDecryption(file, keyPath)).start();
    }
}
