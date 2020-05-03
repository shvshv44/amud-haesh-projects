package managers;

import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Thread;
import pojos.EncryptionLogEventArgs;
import pojos.EncryptionResults;
import pojos.EncryptorParameters;
import processors.DirectoryProcessorInterface;
import ui.UIManager;
import ui.api.UserOptions;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@AllArgsConstructor
public class ApplicationManager{
    private DirectoryProcessorInterface directoryProcessor;
    private UIManager uiManager;
    private JAXBManager jaxbManager;
    private FileIOHandler fileIOHandler;
    private EncryptorParameters parameters;

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
        new File(path+parameters.getEncryptedEnding()).mkdir();
        generateKeys(path);
        File[] filesList = getFilesInDirectory(path);
        if(filesList == null) {
            return;
        }
        directoryProcessor.encryptDirectory(filesList);
    }

    private void startDecrypt() {
        String cipherPath = uiManager.getCipherPath();
        String keyPath = uiManager.getKeyPath();
        File[] filesList = getFilesInDirectory(cipherPath);
        if(filesList == null)
            return;
        new File(cipherPath+parameters.getDecryptedEnding()).mkdir();
        directoryProcessor.decryptDirectory(filesList, keyPath);
    }

    private void finishMenu() {
        try {
            String xmlContent = jaxbManager.marshal(EncryptionResults.getInstance());
            fileIOHandler.writeToFile(parameters.getResultPath(), xmlContent);
            uiManager.printMessage(jaxbManager.unmarshal(EncryptionResults.class, fileIOHandler.readFile(parameters.getResultPath())).toString());
        } catch (IOException | JAXBException e) {
            uiManager.printError("Could not parse to xml.");
        }
        uiManager.printMessage(generateTotalTimeMessage());
        uiManager.printMessage("Hope you enjoyed! Goodbye :)");
    }

    private void generateKeys(String path) {
        String keyPath = path + parameters.getEncryptedEnding() + parameters.getKeyFileName();
        int[] keys = directoryProcessor.generateKeys();
        try {
            fileIOHandler.writeToFile(keyPath, getKeysString(keys));
        } catch (IOException e) {
            uiManager.printError("error writing keys");
        }
    }

    private boolean isTextFile(File file) {
        return file.getPath().endsWith(parameters.getFileType()) && !file.getPath().endsWith(parameters.getKeyFileName());
    }

    private String generateTotalTimeMessage() {
        List<EncryptionLogEventArgs> logs = EncryptionResults.getInstance().getLogList();
        long time = 0;
        for(EncryptionLogEventArgs log : logs) {
            time += log.getOperationLengthInMilliseconds();
        }
        return "the total process took " + time +" millis";
    }

    private File[] getFilesInDirectory(String path) {
        return new File(path).listFiles(this::isTextFile);
    }

    private String getKeysString(int[] keys) {
        StringJoiner formattedString = new StringJoiner(parameters.getSeparator());
        for(int key : keys) {
            formattedString.add(String.valueOf(key));
        }
        return formattedString.toString();
    }
}
