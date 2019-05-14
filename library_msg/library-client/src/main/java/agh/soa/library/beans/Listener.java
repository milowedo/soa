package agh.soa.library.beans;

import javax.annotation.PostConstruct;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "java:jboss/exported/jms/queue/SOA_test")
public class Listener implements MessageListener {

    @PostConstruct
    private void test(){
        System.out.println("mdb created");
    }

    @Override
    public void onMessage(Message msg) {
        try {
            System.out.println("Msg received by typeThree: " + msg.getBody(String.class) + " type: " + msg.getIntProperty("type"));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
