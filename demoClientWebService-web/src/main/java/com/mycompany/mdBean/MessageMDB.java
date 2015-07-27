package com.mycompany.mdBean;

import java.util.LinkedList;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Юрий Кузнецов <kuznetsov_yura@mail.ru>
 */
@MessageDriven(mappedName = "jms/javaee7/Queue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class MessageMDB implements MessageListener {

    private static LinkedList<String> list = new LinkedList<String>();

    public synchronized static LinkedList<String> getList() {
        return list;
    }

    @Resource
    private MessageDrivenContext context;

    @Override
    public void onMessage(Message message) {
        try {
            String xml = message.getBody(String.class);
            addMessage(xml);
            System.out.println("list size = " + list.size());
            System.out.println("Message received:\n" + xml);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addMessage(String xml) {
        list.add(xml);
    }

    public synchronized static String removeFirst() {
        String result = list.removeFirst();
        return result;
    }

    public synchronized static String removeLast() {
        String result = list.removeLast();
        return result;
    }

    public synchronized static void clearMessage() {
        list = new LinkedList<String>();
    }

}
