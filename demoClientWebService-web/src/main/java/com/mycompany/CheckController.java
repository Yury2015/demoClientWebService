package com.mycompany;

import com.mycompany.jaxb.JaxbUtil;
import com.mycompany.jaxb.StringReverseMessage;
import com.mycompany.mdBean.MessageMDB;
import com.mycompany.webservice.MyException_Exception;
import java.util.LinkedList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;

/**
 *
 * @author Юрий Кузнецов <kuznetsov_yura@mail.ru>
 */
@ManagedBean(name = "checkController")
@SessionScoped
public class CheckController {

    private static final Logger log = Logger.getLogger(CheckController.class);
    private LinkedList<String> list;

    @EJB
    ClientService clientServise;

    private String inputText;
    private String resultText = "Введите строку и нажмите кнопку \"GO!\"";
    private String resultAsync = "Очередь асинхронных сообщений пуста.";

    /**
     * Creates a new instance of CheckPalindrome
     */
    public CheckController() {
    }

    public String doCheck() {
        boolean result = true;

        try {
            result = clientServise.splitRevertBusiness(inputText);

        } catch (MyException_Exception e) {
            log.error(e.getMessage());
            resultText = e.getMessage();
            return null;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            resultText = ex.getMessage() + " (возможно, требуется перезапустить сервис.)";
            return null;
        }
        if (result) {
            resultText = "Результат проверки строки: True (строка \"" + inputText + "\" не является полиндромом.)";
        } else {
            resultText = "Результат проверки строки: False (строка \"" + inputText + "\" является полиндромом.)";
        }
        System.out.println("done");
        return null;
    }

    public String doCheckAsync() {
        list = MessageMDB.getList();
        int number = list.size();
        if (0 < number) {
            String xml = MessageMDB.removeFirst();
            resultAsync = "Количество сообщений в очереди: " + number + " : Сообщение :" + xml;
            try {
                StringReverseMessage m1 = JaxbUtil.createStringReverseMessageFromXML(xml);
            } catch (JAXBException ex) {
                log.error(ex.getMessage());
            }
        } else {
            resultAsync = "Очередь асинхронных сообщений пуста.";
        }
        return null;
    }

    public String getInputText() {
        return this.inputText;
    }

    public void setInputText(String txt) {
        this.inputText = txt;
    }

    public String getResultText() {
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    public String getResultAsync() {
        return resultAsync;
    }

    public void setResultAsync(String resultAsync) {
        this.resultAsync = resultAsync;
    }

}
