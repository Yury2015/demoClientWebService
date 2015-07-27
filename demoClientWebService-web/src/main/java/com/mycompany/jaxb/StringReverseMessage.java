package com.mycompany.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Юрий Кузнецов <kuznetsov_yura@mail.ru>
 */
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class StringReverseMessage {

    @XmlElement(name = "request")
    private String string;
    @XmlElement(name = "response")
    private Boolean notPolindrom;

    public StringReverseMessage() {
    }

    public StringReverseMessage(String str, Boolean polindrom) {
        this.string = str;
        this.notPolindrom = polindrom;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Boolean getNotPolindrom() {
        return notPolindrom;
    }

    public void setNotPolindrom(Boolean notPolindrom) {
        this.notPolindrom = notPolindrom;
    }

    public String toStringXML() {
        String result = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<message>\n"
                + "    <request>" + string + "</request>\n"
                + "    <response>" + notPolindrom + "</response>\n"
                + "</message>";
        return String.valueOf(result);
    }
}
