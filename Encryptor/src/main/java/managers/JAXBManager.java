package managers;

import pojos.EncryptionLogEventArgs;
import pojos.EncryptionLogs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class JAXBManager {
    private EncryptionLogs xmlEncryptionLogs;
    private String resultFilePath;

    public JAXBManager(String resultFilePath) {
        this.resultFilePath = resultFilePath;
        this.xmlEncryptionLogs = new EncryptionLogs();
    }

    public void marshal() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(EncryptionLogs.class, EncryptionLogEventArgs.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(xmlEncryptionLogs, new File(resultFilePath));
    }

    public EncryptionLogs unmarshall() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(EncryptionLogEventArgs.class);
        return (EncryptionLogs) context.createUnmarshaller()
                .unmarshal(new FileReader(resultFilePath));
    }

    public void addLog(EncryptionLogEventArgs log) {
        xmlEncryptionLogs.addLog(log);
    }
}
