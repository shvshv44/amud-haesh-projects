package managers;

import encryptors.FileEncryptor;
import models.UserOptions;

public class ApplicationManager {
    private UIManager uiManager;
    private FileEncryptor fileEncryptor;

    public ApplicationManager(FileEncryptor manager, UIManager uiManager) {
        this.uiManager = uiManager;
        this.fileEncryptor = manager;
    }

    public void startMenu() {
        UserOptions choice = UserOptions.getOptionByCodeNumber(0);
        while (choice != UserOptions.EXIT) {
            choice = uiManager.getChoice();

            switch (choice) {
                case ENCRYPT:
                    choseEncrypt();
                    break;
                case DECRYPT:
                    choseDecrypt();
                    break;
            }
        }
        System.out.print("Hope you enjoyed! Goodbye :)");
    }

    private void choseEncrypt() {
        String path = uiManager.getMessagePath();
        fileEncryptor.encrypt(path);
    }

    private void choseDecrypt() {
        String cipherPath = uiManager.getCipherPath();
        String keyPath = uiManager.getKeyPath();
        fileEncryptor.decrypt(cipherPath, keyPath);
    }
}
