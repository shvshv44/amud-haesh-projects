import algorithms.IEncryptionAlgorithm;
import algorithms.ShiftUpEncryption;
import generators.RandomKeyGenerator;
import managers.ApplicationManager;
import encryptors.FileEncryptor;
import managers.FileManager;
import managers.UIManager;

public class Main {
    public static void main(String[] args) {
        IEncryptionAlgorithm substitution = new ShiftUpEncryption();
        FileEncryptor fileEncryptor = new FileEncryptor(substitution, new RandomKeyGenerator(), new FileManager());
        ApplicationManager applicationManager = new ApplicationManager(fileEncryptor, new UIManager());
        applicationManager.startMenu();


        // C:\BEN\Encryptor\messages\ben.txt

        // C:\\Ben\\Encryptor_messages\\ben.txt
    }
}
