package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EncryptionResults")
public class Results {

    @XmlElementRef()
    private List<EncryptionLogEventArgs> logList;

    public Results() {
        this.logList = new LinkedList<>();
    }

    public void addLog(EncryptionLogEventArgs log) {
        logList.add(log);
    }
}
