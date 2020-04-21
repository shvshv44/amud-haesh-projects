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

    public void updateObservers(EncryptionLogEventArgs args) {
        for(Observer observer : observerList) {
            observer.update(args);
        }
    }

    public void updateObservers(String message) {
        for(Observer observer : observerList) {
            observer.update(message);
        }
    }
}
