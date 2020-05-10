package encryptor.processors;

import encryptor.encryptors.FileEncryptor;
import encryptor.pojos.EncryptorParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public abstract class DirectoryProcessor implements DirectoryProcessorInterface {
    protected FileEncryptor fileEncryptor;

    public DirectoryProcessor(FileEncryptor fileEncryptor) {
        this.fileEncryptor = fileEncryptor;
    }

    @Override
    public int[] generateKeys() {
        return fileEncryptor.generateKeys();
    }

    @Override
    public void encryptDirectory(File[] files, String keyPath) {
        for(File file : files) {
            encryptFile(file, keyPath);
        }
    }

    @Override
    public void decryptDirectory(File[] files, String keyPath) {
        for(File file : files)
            decryptFile(file, keyPath);
    }
}
