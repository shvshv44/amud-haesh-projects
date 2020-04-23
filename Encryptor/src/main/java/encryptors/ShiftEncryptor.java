package encryptors;

import algorithms.EncryptionAlgorithm;
import generators.KeyGenerator;
import managers.FileIOHandler;
import managers.JAXBManager;
import pojos.EncryptorParameters;

public class ShiftEncryptor extends FileEncryptor {

    public ShiftEncryptor(JAXBManager jaxbManager, EncryptionAlgorithm algorithm, KeyGenerator keyGenerator, FileIOHandler fileIOHandler, EncryptorParameters parameters) {
        super(jaxbManager, algorithm, keyGenerator, fileIOHandler, parameters);
    }

    @Override
    public String encrypt(String message) {
        int key = getKey();
        return algorithm.encrypt(message, key, parameters.getSeparator());
    }

    @Override
    public String decrypt(String cipher) {
        int key = getKey();
        return algorithm.decrypt(cipher, key, parameters.getSeparator());
    }

    private int getKey() {
        return keys[0]; // the only key is in the first place
    }
}
