package agh.soa.library.beans;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.*;
import java.io.Serializable;
import java.util.logging.Logger;

public class AvailabilityTopicConsumer implements Serializable {
    @Inject
    private JMSContext context;
    @Resource(mappedName = "java:jboss/exported/jms/topic/SOA_Test")
    Topic topic;
    private JMSConsumer consumer;
    private static final Logger logger = Logger.getLogger(AvailabilityTopicConsumer.class.getName());

    public void subscribe() {
        this.consumer = this.
                context.
                createConsumer(topic);
        this.consumer.setMessageListener(new Listener());
        logger.info("AVAILABILITY TOPIC: subscribed");
    }

    public void receiveMessage() {
        try {
            logger.info("AVAILABILITY TOPIC: reading \"" + this.consumer.receiveBody(String.class)+"\"");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
