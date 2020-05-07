package encryptor.processors;

import encryptor.encryptors.FileEncryptor;
import encryptor.pojos.EncryptorParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public abstract class DirectoryProcessor implements DirectoryProcessorInterface {
    protected FileEncryptor fileEncryptor;
    private EncryptorParameters parameters;

    public DirectoryProcessor(FileEncryptor fileEncryptor, EncryptorParameters parameters) {
        this.fileEncryptor = fileEncryptor;
        this.parameters = parameters;
    }

    @Override
    public int[] generateKeys() {
        return fileEncryptor.generateKeys();
    }

    @Override
    public void encryptDirectory(File[] files) {
        for(File file : files) {
            String keyPath = file.getParent() + parameters.getEncryptedFolderName() + parameters.getKeyFileName();
            encryptFile(file, keyPath);
        }
    }

    @Override
    public void decryptDirectory(File[] files, String keyPath) {
        for(File file : files)
            decryptFile(file, keyPath);
    }
}
