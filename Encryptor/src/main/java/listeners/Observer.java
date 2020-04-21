package listeners;

import pojos.EncryptionLogEventArgs;

public abstract class Observer {
    protected Observable observable;

    public Observer(Observable observable) {
        this.observable = observable;
        assignObservable();
    }

    private void assignObservable() {
    observable.addObserver(this);
    }

    public abstract void update(EncryptionLogEventArgs args);

    public abstract void update(String message);

}
