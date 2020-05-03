package listeners;

import pojos.EncryptionLogEventArgs;

public interface Observer {
    void encryptionStarted(String fileName);

    void encryptionEnded(EncryptionLogEventArgs args);

    void decryptionStarted(String fileName);

    void decryptionEnded(EncryptionLogEventArgs args);

}
