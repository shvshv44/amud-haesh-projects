package pojos;

import algorithms.EncryptionAlgorithm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Decryption")
public class DecryptionArgs extends EncryptionLogEventArgs {
    public DecryptionArgs(String originalFileName, String outputFileName, EncryptionAlgorithm algorithmType, long operationLengthInMilliseconds) {
        super(originalFileName, outputFileName, algorithmType, operationLengthInMilliseconds);
    }
}
