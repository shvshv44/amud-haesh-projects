package encryptor.pojos;

import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@XmlRootElement(name = "Encryption")
public class EncryptionArgs extends EncryptionLogEventArgs {
    public EncryptionArgs(String originalFileName, String outputFileName, String algorithmType, long operationLengthInMilliseconds) {
        super(originalFileName, outputFileName, algorithmType, operationLengthInMilliseconds);
    }
}
