package com.mycompany.jaxb;



import java.io.Serializable;

/**
 *
 * @author Юрий Кузнецов <kuznetsov_yura@mail.ru>
 */
public class messageDTO implements Serializable {

    private String string;
    private Boolean notPolindrom;

    public messageDTO() {
    }

    public messageDTO(String string, Boolean notPolindrom) {
        this.string = string;
        this.notPolindrom = notPolindrom;
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

    @Override
    public String toString() {
        String result = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<message>\n"
                + "    <request>" + string + "</request>\n"
                + "    <response>" + notPolindrom + "</response>\n"
                + "</message>";
        return String.valueOf(result);
    }
    
}
