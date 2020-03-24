import algorithms.shift.ShiftUpEncryption;
import encryptors.Encryptor;
import encryptors.RepeatEncryptor;
import encryptors.ShiftEncryptor;
import generators.RandomKeyGenerator;
import managers.ApplicationManager;
import encryptors.FileEncryptor;
import managers.FileManager;
import managers.UIManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = getProperties();
        Encryptor encryptor = new RepeatEncryptor(new ShiftUpEncryption(properties), 2, new RandomKeyGenerator());
        encryptor = new ShiftEncryptor(new ShiftUpEncryption(properties));
        FileEncryptor fileEncryptor = new FileEncryptor(encryptor, new RandomKeyGenerator(), new FileManager(), properties);
        ApplicationManager applicationManager = new ApplicationManager(fileEncryptor, new UIManager());
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

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            return prop;
        }
    }
}
