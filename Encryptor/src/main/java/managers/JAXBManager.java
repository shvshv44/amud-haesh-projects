package managers;

import com.sun.org.apache.regexp.internal.RE;
import lombok.AllArgsConstructor;
import pojos.EncryptionLogEventArgs;
import pojos.Results;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class JAXBManager {
    private Results xmlresults;
    private String resultFilePath;

    public JAXBManager(String resultFilePath) {
        this.resultFilePath = resultFilePath;
        this.xmlresults = new Results();
    }

    public void marshal() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Results.class, EncryptionLogEventArgs.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(xmlresults, new File(resultFilePath));
    }

    public Results unmarshall() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(EncryptionLogEventArgs.class);
        return (Results) context.createUnmarshaller()
                .unmarshal(new FileReader(resultFilePath));
    }

    public void addResult(EncryptionLogEventArgs result) {
        xmlresults.addLog(result);
    }
}
