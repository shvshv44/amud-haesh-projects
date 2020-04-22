package loggers;

import listeners.Observer;
import pojos.EncryptionLogEventArgs;

public class EncryptionLogger implements Observer {
    @Override
    public void encryptionStarted() {
        System.out.println("starting to encrypt");
    }

    @Override
    public void encryptionEnded(EncryptionLogEventArgs args) {
        System.out.println("encrypting the source file " + args.getOriginalFileName() +
                " with the " + args.getAlgorithmType().getClass().getSimpleName() +
                " algorithm took " + args.getOperationLengthInMilliseconds() + " millis." +
                "\nthe new file is in " + args.getOutputFileName());
    }

    @Override
    public void decryptionStarted() {
        System.out.println("starting to decrypt");
    }

    @Override
    public void decryptionEnded(EncryptionLogEventArgs args) {
        System.out.println("decrypting the source file " + args.getOriginalFileName() +
                " with the " + args.getAlgorithmType().getClass().getSimpleName() +
                " algorithm took " + args.getOperationLengthInMilliseconds() + " millis." +
                "\nthe new file is in " + args.getOutputFileName());
    }
}
