package com.mycompany.jaxb;

import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Юрий Кузнецов <kuznetsov_yura@mail.ru>
 */
public class JaxbUtil {

    public static StringReverseMessage createStringReverseMessageFromXML(String xml) throws JAXBException {
        StringReader reader = new StringReader(xml);
        JAXBContext context = JAXBContext.newInstance(StringReverseMessage.class);
        Unmarshaller u = context.createUnmarshaller();

        StringReverseMessage message = (StringReverseMessage) u.unmarshal(reader);
        return message;
    }

    public static messageDTO createMessageDTOFromXML(String xml) throws JAXBException {
        StringReader reader = new StringReader(xml);
        JAXBContext context = JAXBContext.newInstance();
        Unmarshaller u = context.createUnmarshaller();

        messageDTO message = (messageDTO) u.unmarshal(reader);
        return message;
    }

}
