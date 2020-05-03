package processors;

import encryptors.FileEncryptor;

import java.io.File;

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
    public void encryptDirectory(File[] files) {
        for(File file : files)
            encryptFile(file);
    }

    @Override
    public void decryptDirectory(File[] files, String keyPath) {
        for(File file : files)
            decryptFile(file, keyPath);
    }
}
