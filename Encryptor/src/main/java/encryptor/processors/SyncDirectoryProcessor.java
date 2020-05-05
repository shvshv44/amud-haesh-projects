package encryptor.processors;

import encryptor.encryptors.FileEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SyncDirectoryProcessor extends DirectoryProcessor {

    public SyncDirectoryProcessor(@Qualifier("shiftEnc") FileEncryptor fileEncryptor) {
        super(fileEncryptor);
    }

    @Override
    public void encryptFile(File file) {
        fileEncryptor.startEncryption(file);
    }

    @Override
    public void decryptFile(File file, String keyPath) {
        fileEncryptor.startDecryption(file, keyPath);
    }
}
