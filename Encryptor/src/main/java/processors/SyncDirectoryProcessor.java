package processors;

import encryptors.FileEncryptor;

import java.io.File;

public class SyncDirectoryProcessor extends DirectoryProcessor{

    public SyncDirectoryProcessor(FileEncryptor fileEncryptor) {
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
