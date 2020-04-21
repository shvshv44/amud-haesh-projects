package loggers;

import listeners.Observable;
import listeners.Observer;
import pojos.EncryptionLogEventArgs;

public class EncryptionLogger extends Observer {
    public EncryptionLogger(Observable observable) {
        super(observable);
    }

    @Override
    public void update(EncryptionLogEventArgs args) {
        long operationTime = args.getEndTime() - args.getStartTime();
        System.out.println(args.getOperation() + " the source file " + args.getSourceFileName() +
                " with the " + args.getAlgorithm().getClass().getSimpleName() +
                " algorithm took " + operationTime + " millis." +
                "\nthe new file is in " + args.getDestinationFileName());

    }

    @Override
    public void update(String message) {
        System.out.println(message);
    }
}
