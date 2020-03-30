import algorithms.shift.ShiftUpEncryption;
import encryptors.RepeatEncryptor;
import encryptors.ShiftEncryptor;
import generators.KeyGenerator;
import generators.RandomKeyGenerator;
import managers.ApplicationManager;
import encryptors.Encryptor;
import managers.FileManager;
import managers.UIManager;

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

        KeyGenerator keyGenerator = new RandomKeyGenerator();

        Encryptor encryptor = new RepeatEncryptor(new ShiftUpEncryption(), keyGenerator, new FileManager(),
                separator, pathSeparator, encryptedEnding, decryptedEnding, keyFileName, 20);
        //encryptor = new ShiftEncryptor(new ShiftUpEncryption(), keyGenerator, new FileManager(),
          //      separator, pathSeparator, encryptedEnding, decryptedEnding, keyFileName);


        ApplicationManager applicationManager = new ApplicationManager(encryptor, new UIManager());
        applicationManager.startMenu();

        // C:\BEN\Encryptor_messages\ben.txt

        // C:\\Ben\\Encryptor_messages\\ben.txt
    }

    public static Properties getProperties() {
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream(
                    "C:\\BEN\\Encryptor\\amud-haesh-projects\\Encryptor\\src\\resources\\Properties.properties");
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
