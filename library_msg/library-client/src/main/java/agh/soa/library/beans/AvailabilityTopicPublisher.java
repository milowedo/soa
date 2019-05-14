package agh.soa.library.beans;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Topic;
import java.io.Serializable;
import java.util.logging.Logger;

@Named(value = "availabilityTopicPublisher")
@SessionScoped
public class AvailabilityTopicPublisher implements Serializable {

    @Resource(mappedName = "java:jboss/exported/jms/topic/SOA_Test")
    private Topic topic;

    @Inject
    private JMSContext jmsContext;

    private static final Logger logger = Logger.getLogger(AvailabilityTopicPublisher.class.getName());

    public void sendMessageNewBook(String txt) {
        try {
            Message msg = jmsContext.createTextMessage(txt);
            msg.setJMSCorrelationID("ID:NEW");
            jmsContext.createProducer().send(topic, msg);
            logger.info("AVAILABILITY TOPIC, sending \"" + txt + "\"");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void sendMessageBookAvailable(String txt) {
        try {
            Message msg = jmsContext.createTextMessage(txt);
            msg.setIntProperty("type", 1);
            logger.info("AVAILABILITY TOPIC, sending \"" + txt + "\"");
            jmsContext.createProducer().send(topic, msg);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
