package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.StringJoiner;

@Data
@XmlType
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement()
@XmlSeeAlso({EncryptionArgs.class, DecryptionArgs.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class EncryptionLogEventArgs {
    @XmlElement(name = "OriginalFileName")
    private String originalFileName;
    @XmlElement(name = "OutputFileName")
    private String outputFileName;
    @XmlElement(name = "AlgorithmType")
    private String algorithmType;
    @XmlElement(name = "OperationLengthInMilliseconds")
    private long operationLengthInMilliseconds;


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof EncryptionLogEventArgs))
            return false;

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
