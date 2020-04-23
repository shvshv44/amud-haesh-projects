package managers;

import encryptors.FileEncryptor;
import models.UserOptions;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class ApplicationManager {
    private UIManager uiManager;
    private FileEncryptor fileEncryptor;
    private JAXBManager jaxbManager;
    public ApplicationManager(FileEncryptor fileEncryptor, UIManager uiManager, JAXBManager jaxbManager) {
        this.uiManager = uiManager;
        this.fileEncryptor = fileEncryptor;
        this.jaxbManager = jaxbManager;
    }

    public void startMenu() {
        UserOptions choice = UserOptions.getOptionByCodeNumber(0);
        while (choice != UserOptions.EXIT) {
            choice = uiManager.getChoice();

            switch (choice) {
                case ENCRYPTION:
                    startEncrypt();
                    break;
                case DECRYPTION:
                    startDecrypt();
                    break;
            }
        }
        finishMenu();
    }

    private void startEncrypt() {
        String path = uiManager.getMessagePath();
        fileEncryptor.startEncryption(path);
    }

    private void startDecrypt() {
        String cipherPath = uiManager.getCipherPath();
        String keyPath = uiManager.getKeyPath();
        fileEncryptor.startDecryption(cipherPath, keyPath);
    }

    private void finishMenu() {
        try {
            jaxbManager.marshal();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not parse to xml.");
        }
        uiManager.printFinishMessage();
    }
}
