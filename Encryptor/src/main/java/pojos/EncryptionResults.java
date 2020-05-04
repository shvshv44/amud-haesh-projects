package pojos;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class EncryptionResults {
    private static EncryptionResults results;

    @XmlElementRef()
    private List<EncryptionLogEventArgs> logList;

    public static EncryptionResults getInstance() {
        if(results == null)
            results = new EncryptionResults();
        return results;
    }

    private EncryptionResults() {
        this.logList = new LinkedList<>();
    }
}
