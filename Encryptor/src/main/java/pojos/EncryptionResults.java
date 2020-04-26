package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
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
