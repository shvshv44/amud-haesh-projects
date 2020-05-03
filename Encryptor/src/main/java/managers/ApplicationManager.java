package managers;

import lombok.AllArgsConstructor;
import pojos.EncryptionLogEventArgs;
import pojos.EncryptionResults;
import processors.DirectoryProcessorInterface;
import ui.UIManager;
import ui.api.UserOptions;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class ApplicationManager{
    private DirectoryProcessorInterface directoryProcessor;
    private UIManager uiManager;
    private JAXBManager jaxbManager;
    private FileIOHandler fileIOHandler;
    private String resultFilePath;
    private String encryptedFolderName;
    private String decryptedFolderName;

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
        directoryProcessor.generateKeys();
        File[] filesList = new File(path).listFiles();
        if(filesList == null)
            return;
        new File(path+encryptedFolderName).mkdir();
        encryptFolder(filesList);
    }

    private void startDecrypt() {
        String cipherPath = uiManager.getCipherPath();
        String keyPath = uiManager.getKeyPath();
        File[] filesList = new File(cipherPath).listFiles();
        if(filesList == null)
            return;
        new File(cipherPath+decryptedFolderName).mkdir();
        decryptFolder(filesList, keyPath);
    }

    private void encryptFolder(File[] folder) {
        for(File file : folder) {
            if(isTextFile(file))
                directoryProcessor.encryptFile(file);
        }
    }

    private void decryptFolder(File[] folder, String keyPath) {
        for(File file : folder) {
            if(isTextFile(file))
                directoryProcessor.decryptFile(file, keyPath);
        }
    }

    private void finishMenu() {
        try {
            String xmlContent = jaxbManager.marshal(EncryptionResults.getInstance());
            fileIOHandler.writeToFile(resultFilePath, xmlContent);
            System.out.println(jaxbManager.unmarshal(EncryptionResults.class, fileIOHandler.readFile(resultFilePath)));
        } catch (IOException | JAXBException e) {
            uiManager.printError("Could not parse to xml.");
        }
        uiManager.printMessage(generateTotalTimeMessage());
        uiManager.printMessage("Hope you enjoyed! Goodbye :)");
    }

    private boolean isTextFile(File file) {
        return file.getPath().endsWith(".txt") && !file.getPath().endsWith("key.txt");
    }

    private String generateTotalTimeMessage() {
        List<EncryptionLogEventArgs> logs = EncryptionResults.getInstance().getLogList();
        long time = 0;
        for(EncryptionLogEventArgs log : logs) {
            time += log.getOperationLengthInMilliseconds();
        }
        return "the total process took " + time +" millis";
    }
}
