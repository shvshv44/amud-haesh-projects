package encryptor.pojos;

import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@XmlRootElement(name = "Decryption")
public class DecryptionArgs extends EncryptionLogEventArgs {
    public DecryptionArgs(String originalFileName, String outputFileName, String algorithmType, long operationLengthInMilliseconds) {
        super(originalFileName, outputFileName, algorithmType, operationLengthInMilliseconds);
    }
}
