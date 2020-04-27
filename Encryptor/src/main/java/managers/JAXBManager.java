package managers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


public class JAXBManager<T> {
    public String marshal(T object) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter xmlContent = new StringWriter();
        marshaller.marshal(object, xmlContent);
        return xmlContent.toString();
    }

    public T unmarshal(Class<T> tClass, String xmlContent) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(tClass);
        return (T) context.createUnmarshaller()
                .unmarshal(new StringReader(xmlContent));
    }
}
