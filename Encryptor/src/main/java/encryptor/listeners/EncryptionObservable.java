package encryptor.listeners;

import org.springframework.stereotype.Component;
import encryptor.pojos.EncryptionLogEventArgs;

import java.util.LinkedList;
import java.util.List;

@Component
public abstract class EncryptionObservable {
    private List<EncryptionObserver> observerList;

    public EncryptionObservable() {
        this.observerList = new LinkedList<>();
    }

    public EncryptionObservable(List<EncryptionObserver> observerList) {
        this.observerList = observerList;
    }

    public void addObserver(EncryptionObserver observer) {
        observerList.add(observer);
    }

    public void removeObserver(EncryptionObserver observer) {
        observerList.remove(observer);
    }

    protected void encryptionStarted(String fileName) {
        for(EncryptionObserver observer : observerList)
            observer.encryptionStarted(fileName);
    }
    protected void encryptionEnded(EncryptionLogEventArgs args) {
        for(EncryptionObserver observer : observerList)
            observer.encryptionEnded(args);
    }
    protected void decryptionStarted(String fileName) {
        for(EncryptionObserver observer : observerList)
            observer.decryptionStarted(fileName);
    }
    protected void decryptionEnded(EncryptionLogEventArgs args) {
        for(EncryptionObserver observer : observerList)
            observer.decryptionEnded(args);
    }
}
