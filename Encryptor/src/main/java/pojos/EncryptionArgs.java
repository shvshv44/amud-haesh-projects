package pojos;

import algorithms.EncryptionAlgorithm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Encryption")
public class EncryptionArgs extends EncryptionLogEventArgs {
    public EncryptionArgs(String originalFileName, String outputFileName, EncryptionAlgorithm algorithmType, long operationLengthInMilliseconds) {
        super(originalFileName, outputFileName, algorithmType, operationLengthInMilliseconds);
    }
}
