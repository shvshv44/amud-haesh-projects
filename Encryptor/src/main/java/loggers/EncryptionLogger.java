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
        long operationTime = args.getEndTime() - args.getStartTime();
        System.out.println("encrypting the source file " + args.getSourceFileName() +
                " with the " + args.getAlgorithm().getClass().getSimpleName() +
                " algorithm took " + operationTime + " millis." +
                "\nthe new file is in " + args.getDestinationFileName());
    }

    @Override
    public void decryptionStarted() {
        System.out.println("starting to decrypt");
    }

    @Override
    public void decryptionEnded(EncryptionLogEventArgs args) {
        long operationTime = args.getEndTime() - args.getStartTime();
        System.out.println("decrypting the source file " + args.getSourceFileName() +
                " with the " + args.getAlgorithm().getClass().getSimpleName() +
                " algorithm took " + operationTime + " millis." +
                "\nthe new file is in " + args.getDestinationFileName());
    }
}
