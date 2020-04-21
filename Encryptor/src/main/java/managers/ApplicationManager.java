package managers;

import encryptors.FileEncryptor;
import models.UserOptions;

public class ApplicationManager {
    private UIManager uiManager;
    private FileEncryptor fileEncryptor;

    public ApplicationManager(FileEncryptor fileEncryptor, UIManager uiManager) {
        this.uiManager = uiManager;
        this.fileEncryptor = fileEncryptor;
    }

    public void startMenu() {
        UserOptions choice = UserOptions.getOptionByCodeNumber(0);
        while (choice != UserOptions.EXIT) {
            choice = uiManager.getChoice();

            switch (choice) {
                case ENCRYPTION:
                    choseEncrypt();
                    break;
                case DECRYPTION:
                    choseDecrypt();
                    break;
            }
        }
        System.out.print("Hope you enjoyed! Goodbye :)");
    }

    private void choseEncrypt() {
        String path = uiManager.getMessagePath();
        fileEncryptor.startEncryption(path);
    }

    private void choseDecrypt() {
        String cipherPath = uiManager.getCipherPath();
        String keyPath = uiManager.getKeyPath();
        fileEncryptor.startDecryption(cipherPath, keyPath);
    }
}
