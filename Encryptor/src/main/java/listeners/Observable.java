package listeners;

import pojos.EncryptionLogEventArgs;

import java.util.LinkedList;
import java.util.List;

public abstract class Observable {
    private List<Observer> observerList;

    public Observable() {
        this.observerList = new LinkedList<>();
    }

    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    protected void encryptionStarted() {
        for(Observer observer : observerList)
            observer.encryptionStarted();
    }
    protected void encryptionEnded(EncryptionLogEventArgs args) {
        for(Observer observer : observerList)
            observer.encryptionEnded(args);
    }
    protected void decryptionStarted() {
        for(Observer observer : observerList)
            observer.decryptionStarted();
    }
    protected void decryptionEnded(EncryptionLogEventArgs args) {
        for(Observer observer : observerList)
            observer.decryptionEnded(args);
    }
}
