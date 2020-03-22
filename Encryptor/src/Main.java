import algorithms.IEncryptionAlgorithm;
import algorithms.shift.ShiftMultiplyEncryption;
import algorithms.shift.ShiftUpEncryption;
import encryptors.IEncryptor;
import encryptors.RepeatEncryptor;
import encryptors.ShiftEncryptor;
import generators.RandomKeyGenerator;
import managers.ApplicationManager;
import encryptors.FileEncryptor;
import managers.FileManager;
import managers.UIManager;

public class Main {
    public static void main(String[] args) {
        IEncryptor encryptor = new RepeatEncryptor(new ShiftUpEncryption(), 2, new RandomKeyGenerator());
        encryptor = new ShiftEncryptor(new ShiftUpEncryption());
        FileEncryptor fileEncryptor = new FileEncryptor(encryptor, new RandomKeyGenerator(), new FileManager());
        ApplicationManager applicationManager = new ApplicationManager(fileEncryptor, new UIManager());
        applicationManager.startMenu();


        // C:\BEN\Encryptor_messages\ben.txt

        // C:\\Ben\\Encryptor_messages\\ben.txt
    }
}
