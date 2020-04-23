package pojos;

import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
@NoArgsConstructor
public class EncryptionArgs extends EncryptionLogEventArgs {
    public EncryptionArgs(String originalFileName, String outputFileName, String algorithmType, long operationLengthInMilliseconds) {
        super(originalFileName, outputFileName, algorithmType, operationLengthInMilliseconds);
    }
}
