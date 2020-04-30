package processors;

import encryptors.FileEncryptor;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public abstract class DirectoryProcessor implements DirectoryProcessorInterface {
    protected FileEncryptor fileEncryptor;

    @Override
    public void generateKeys() {
        fileEncryptor.generateKeys();
    }
}
