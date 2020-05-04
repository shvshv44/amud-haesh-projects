import algorithms.shift.ShiftUpEncryption;
import encryptors.FileEncryptor;
import encryptors.RepeatEncryptor;
import generators.KeyGenerator;
import generators.RandomKeyGenerator;
import loggers.EncryptionLogger;
import managers.ApplicationManager;
import managers.FileIOHandler;
import managers.JAXBManager;
import pojos.EncryptionResults;
import pojos.EncryptorParameters;
import processors.AsyncDirectoryProcessor;
import processors.DirectoryProcessorInterface;
import ui.UIManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Properties properties = getProperties();
        String separator = properties.getProperty("separator");
        String pathSeparator = properties.getProperty("pathSeparator");
        String encryptedEnding = properties.getProperty("encryptedFileEnding");
        String decryptedEnding = properties.getProperty("decryptedFileEnding");
        String keyFileName = properties.getProperty("keyFileName");
        String resultPath = properties.getProperty("resultFilePath");
        String fileType = properties.getProperty("filesType");

        UIManager uiManager = new UIManager();
        FileIOHandler fileIOHandler = new FileIOHandler();
        JAXBManager<EncryptionResults> jaxbManager = new JAXBManager<>();
        KeyGenerator keyGenerator = new RandomKeyGenerator();
        EncryptorParameters parameters = new EncryptorParameters(separator, pathSeparator, encryptedEnding, decryptedEnding, keyFileName, fileType, resultPath);

        FileEncryptor fileEncryptor = new RepeatEncryptor(new ShiftUpEncryption(), keyGenerator, fileIOHandler, uiManager, 10, parameters);
        EncryptionLogger logger = new EncryptionLogger(uiManager);
        fileEncryptor.addObserver(logger);

        DirectoryProcessorInterface directoryProcessor = new AsyncDirectoryProcessor(fileEncryptor);

        ApplicationManager applicationManager = new ApplicationManager(directoryProcessor, uiManager, jaxbManager, fileIOHandler, parameters);
        applicationManager.startMenu();

        // C:\\BEN\\Encryptor_messages\\ben.txt
        // C:\\Ben\\Encryptor_messages\\encrypted
        // C:\\Ben\\Encryptor_messages\\encrypted\key.txt
    }

    public static Properties getProperties() {
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream(
                    "C:\\BEN\\Encryptor\\amud-haesh-projects\\Encryptor\\src\\main\\resources\\application.properties");
            // load a properties file
            prop.load(input);
        } catch (IOException e) {
            System.err.println("Error while reaching properties file.");
            exit(1);
        } finally {
            return prop;
        }
    }
}
