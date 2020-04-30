package processors;

import encryptors.FileEncryptor;

import java.io.File;

public class AsyncDirectoryProcessor extends DirectoryProcessor {
    public AsyncDirectoryProcessor(FileEncryptor fileEncryptor) {
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
