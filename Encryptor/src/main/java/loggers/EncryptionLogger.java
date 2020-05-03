package loggers;

import listeners.Observer;
import pojos.EncryptionLogEventArgs;
import ui.UIManager;

public class EncryptionLogger implements Observer {
    private UIManager uiManager;
    public  EncryptionLogger(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    @Override
    public void encryptionStarted(String fileName) {
        uiManager.printMessage("starting to encrypt the file " + fileName);
    }

    @Override
    public void encryptionEnded(EncryptionLogEventArgs args) {
        uiManager.printMessage("encrypting the source file " + args.getOriginalFileName() +
                " with the " + args.getAlgorithmType().getClass().getSimpleName() +
                " algorithm took " + args.getOperationLengthInMilliseconds() + " millis." +
                "\nthe new file is in " + args.getOutputFileName());
    }

    @Override
    public void decryptionStarted(String fileName) {
        uiManager.printMessage("starting to decrypt the file " + fileName);
    }

    @Override
    public void decryptionEnded(EncryptionLogEventArgs args) {
        uiManager.printMessage("decrypting the source file " + args.getOriginalFileName() +
                " with the " + args.getAlgorithmType().getClass().getSimpleName() +
                " algorithm took " + args.getOperationLengthInMilliseconds() + " millis." +
                "\nthe new file is in " + args.getOutputFileName());
    }
}
