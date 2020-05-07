package encryptor.processors;

import encryptor.encryptors.FileEncryptor;
import encryptor.pojos.EncryptorParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class AsyncDirectoryProcessor extends DirectoryProcessor {

    public AsyncDirectoryProcessor(@Qualifier("repeatEnc") FileEncryptor fileEncryptor,
                                   EncryptorParameters parameters) {
        super(fileEncryptor, parameters);
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
