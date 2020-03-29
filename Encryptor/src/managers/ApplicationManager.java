package managers;

import encryptors.Encryptor;
import models.UserOptions;

public class ApplicationManager {
    private UIManager uiManager;
    private Encryptor fileEncryptor;

    public ApplicationManager(Encryptor fileEncryptor, UIManager uiManager) {
        this.uiManager = uiManager;
        this.fileEncryptor = fileEncryptor;
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
        fileEncryptor.startEncryption(path);
    }

    private void choseDecrypt() {
        String cipherPath = uiManager.getCipherPath();
        String keyPath = uiManager.getKeyPath();
        fileEncryptor.startDecryption(cipherPath, keyPath);
    }
}
