package encryptor.pojos;

import lombok.Getter;
import org.springframework.context.annotation.Scope;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@Getter
@Scope
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

    public void addOldResults(EncryptionResults results) {
        getInstance().getLogList().addAll(results.getLogList());
    }

    public void removeResult(EncryptionLogEventArgs args) {
        getInstance().getLogList().remove(args);
    }
}
