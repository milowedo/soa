package agh.soa.library.beans;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.io.Serializable;
import java.util.logging.Logger;

public class ConfirmationQueue implements Serializable {
    @Resource(mappedName = "java:jboss/exported/jms/queue/SOA_test")
    private Queue queueTest;
    @Inject
    private JMSContext context;

    private static final Logger logger = Logger.getLogger(ConfirmationQueue.class.getName());

    public void sendMessage(String txt) {
        try {
            logger.info("MESSAGE SENDER, sending \" " + txt + "\"");
            context.createProducer().send(queueTest, txt);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}