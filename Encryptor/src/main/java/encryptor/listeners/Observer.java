package encryptor.listeners;

import org.springframework.stereotype.Component;
import encryptor.pojos.EncryptionLogEventArgs;

@Component
public interface Observer {
    void encryptionStarted(String fileName);

    void encryptionEnded(EncryptionLogEventArgs args);

    void decryptionStarted(String fileName);

    void decryptionEnded(EncryptionLogEventArgs args);

}
