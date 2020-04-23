package pojos;

import algorithms.EncryptionAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.StringJoiner;

@Data
@AllArgsConstructor
@XmlRootElement(name = "EncryptionResult")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class EncryptionLogEventArgs {
    @XmlElement(name = "OriginalFileName")
    private String originalFileName;
    @XmlElement(name = "OutputFileName")
    private String outputFileName;
    @XmlElement(name = "AlgorithmType")
    private EncryptionAlgorithm algorithmType;
    @XmlElement(name = "OperationLengthInMilliseconds")
    private long operationLengthInMilliseconds;


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof EncryptionLogEventArgs)) {
            return false;
        }

        EncryptionLogEventArgs args = (EncryptionLogEventArgs) obj;
        return hashCode() == args.hashCode();
    }

    @Override
    public int hashCode() {
        StringJoiner joinedStrings = new StringJoiner("\n")
                .add(originalFileName)
                .add(outputFileName)
                .add(algorithmType.toString())
                .add(Long.toString(operationLengthInMilliseconds));
        return joinedStrings.hashCode();
    }
}
