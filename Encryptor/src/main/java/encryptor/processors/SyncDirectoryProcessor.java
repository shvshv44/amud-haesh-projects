package encryptor.processors;

import encryptor.encryptors.FileEncryptor;
import encryptor.pojos.EncryptorParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SyncDirectoryProcessor extends DirectoryProcessor {

    public SyncDirectoryProcessor(@Qualifier("shiftEnc") FileEncryptor fileEncryptor,
                                  EncryptorParameters parameters) {
        super(fileEncryptor, parameters);
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
