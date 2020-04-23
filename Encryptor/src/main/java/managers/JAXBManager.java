package managers;

import pojos.EncryptionLogEventArgs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JAXBManager {

    public void marshal(EncryptionLogEventArgs args) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(EncryptionLogEventArgs.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(args, new File("C:\\BEN\\Encryptor_messages\\result.xml"));
    }

    public EncryptionLogEventArgs unmarshall() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(EncryptionLogEventArgs.class);
        return (EncryptionLogEventArgs) context.createUnmarshaller()
                .unmarshal(new FileReader("C:\\BEN\\Encryptor_messages\\result.xml"));
    }
}
