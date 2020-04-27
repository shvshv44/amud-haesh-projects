package managers;

import encryptors.FileEncryptor;
import lombok.AllArgsConstructor;
import uiapi.UserOptions;
import pojos.EncryptionResults;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

@AllArgsConstructor
public class ApplicationManager {
    private FileEncryptor fileEncryptor;
    private UIManager uiManager;
    private JAXBManager jaxbManager;
    private FileIOHandler fileIOHandler;
    private String resultFilePath;

    public void startMenu() {
        UserOptions choice = UserOptions.DEFAULT;
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
        fileEncryptor.generateKeys();
        File[] filesList = new File(path).listFiles();
        new File(path+"encrypted").mkdir();
        for(File file : filesList) {
            if(isTextFile(file))
                fileEncryptor.startEncryption(file);
        }
    }

    private void startDecrypt() {
        String cipherPath = uiManager.getCipherPath();
        String keyPath = uiManager.getKeyPath();
        File[] filesList = new File(cipherPath).listFiles();
        new File(cipherPath+"decrypted").mkdir();
        for(File file : filesList) {
            if(isTextFile(file))
                fileEncryptor.startDecryption(file, keyPath);
        }
    }

    private void finishMenu() {
        try {
            String xmlContent = jaxbManager.marshal(EncryptionResults.getInstance());
            fileIOHandler.writeToFile(resultFilePath, xmlContent);
            System.out.println(jaxbManager.unmarshal(EncryptionResults.class, fileIOHandler.readFile(resultFilePath)));
        } catch (IOException | JAXBException e) {
            System.err.println("Could not parse to xml.");
        }
        uiManager.printMessage("Hope you enjoyed! Goodbye :)");
    }

    private boolean isTextFile(File file) {
        return file.getPath().endsWith(".txt") && !file.getPath().endsWith("key.txt");
    }
}
