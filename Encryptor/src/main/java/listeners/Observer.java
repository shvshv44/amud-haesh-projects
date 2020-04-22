package listeners;

import pojos.EncryptionLogEventArgs;

public interface Observer {
    void encryptionStarted();

    void encryptionEnded(EncryptionLogEventArgs args);

    void decryptionStarted();

    void decryptionEnded(EncryptionLogEventArgs args);

}
