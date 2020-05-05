package encryptor.listeners;

import org.springframework.stereotype.Component;
import encryptor.pojos.EncryptionLogEventArgs;

import java.util.LinkedList;
import java.util.List;

@Component
public abstract class Observable {
    private List<Observer> observerList;

    public Observable() {
        this.observerList = new LinkedList<>();
    }

    public Observable(List<Observer> observerList) {
        this.observerList = observerList;
    }

    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    protected void encryptionStarted(String fileName) {
        for(Observer observer : observerList)
            observer.encryptionStarted(fileName);
    }
    protected void encryptionEnded(EncryptionLogEventArgs args) {
        for(Observer observer : observerList)
            observer.encryptionEnded(args);
    }
    protected void decryptionStarted(String fileName) {
        for(Observer observer : observerList)
            observer.decryptionStarted(fileName);
    }
    protected void decryptionEnded(EncryptionLogEventArgs args) {
        for(Observer observer : observerList)
            observer.decryptionEnded(args);
    }
}
