package encryptor.managers;

import encryptor.validators.XMLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import encryptor.pojos.EncryptionLogEventArgs;
import encryptor.pojos.EncryptionResults;
import encryptor.pojos.EncryptorParameters;
import encryptor.processors.DirectoryProcessorInterface;
import encryptor.ui.UIManager;
import encryptor.ui.api.UserOptions;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

@Component
public class ApplicationManager {
    private DirectoryProcessorInterface directoryProcessor;
    private UIManager uiManager;
    private JAXBManager<EncryptionResults> jaxbManager;
    private FileIOHandler fileIOHandler;
    private XMLValidator validator;
    private EncryptorParameters parameters;

    @Autowired
    public ApplicationManager(@Qualifier("asynProcessor") DirectoryProcessorInterface directoryProcessor,
                              UIManager uiManager, JAXBManager<EncryptionResults> jaxbManager, FileIOHandler fileIOHandler, XMLValidator validator, EncryptorParameters parameters) {
        this.directoryProcessor = directoryProcessor;
        this.uiManager = uiManager;
        this.jaxbManager = jaxbManager;
        this.fileIOHandler = fileIOHandler;
        this.validator = validator;
        this.parameters = parameters;
    }

    public void run() {
        startMenu();
        mergeOldAndNewResults();
        finish();
    }

    private void startMenu() {
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
        uiManager.printMessage(generateTotalTimeMessage());
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
        new File(cipherPath+parameters.getDecryptedEnding()).mkdir();
        String keyPath = uiManager.getKeyPath();
        File[] filesList = getFilesInDirectory(cipherPath);
        if(filesList == null) {
            return;
        }
        directoryProcessor.decryptDirectory(filesList, keyPath);
    }

    private void mergeOldAndNewResults() {
        try {
            EncryptionResults.getInstance().addOldResults(jaxbManager.unmarshal(
                    EncryptionResults.class, fileIOHandler.readFile(parameters.getResultPath())));
        } catch (JAXBException e) {
            uiManager.printError("could not parse results xml file to results object");
        } catch (IOException e) {
            uiManager.printError("could not read results from path. check if file exist");
        }
    }

    private void finish() {
        try {
            String xmlContent = jaxbManager.marshal(EncryptionResults.getInstance());
            if(validator.validate(xmlContent, parameters.getXsdValidationFilePath())) {
                fileIOHandler.writeToFile(parameters.getResultPath(), xmlContent);
                uiManager.printMessage(EncryptionResults.getInstance().toString());
            }
        } catch (JAXBException e) {
            uiManager.printError("Could not parse to xml.");
        } catch (IOException e) {
            uiManager.printError("Could not write to file.");
        }
        uiManager.printMessage("Hope you enjoyed! Goodbye :)");
    }

    private void generateKeys(String path) {
        String keyPath = path + parameters.getEncryptedEnding() + parameters.getKeyFileName();
        int[] keys = directoryProcessor.generateKeys();
        try {
            fileIOHandler.writeToFile(keyPath, getKeysString(keys));
        } catch (IOException e) {
            uiManager.printError("Error writing keys");
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
