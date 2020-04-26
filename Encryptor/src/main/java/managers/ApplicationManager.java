package managers;

import encryptors.FileEncryptor;
import lombok.AllArgsConstructor;
import models.UserOptions;
import pojos.EncryptionResults;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@AllArgsConstructor
public class ApplicationManager {
    private FileEncryptor fileEncryptor;
    private UIManager uiManager;
    private JAXBManager jaxbManager;
    private FileIOHandler fileIOHandler;
    private String resultFilePath;

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
            String xmlContent = jaxbManager.marshal(EncryptionResults.getInstance());
            fileIOHandler.writeToFile(resultFilePath, xmlContent);
            System.out.println(jaxbManager.unmarshal(EncryptionResults.class, fileIOHandler.readFile(resultFilePath)));
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            System.err.println("Could not parse to xml.");
        }
        uiManager.printFinishMessage();
    }
}
