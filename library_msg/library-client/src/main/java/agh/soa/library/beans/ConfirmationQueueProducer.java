package agh.soa.library.beans;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;
import java.io.Serializable;
import java.util.logging.Logger;

@Named(value = "confirmationQueueProducer")
public class ConfirmationQueueProducer implements Serializable {
    @Resource(mappedName = "java:jboss/exported/jms/queue/SOA_test")
    private Queue queueTest;
    @Inject
    private JMSContext context;

    private static final Logger logger = Logger.getLogger(ConfirmationQueueProducer.class.getName());

    public void sendMessage(String txt, String user) {
        try {
            Message msg = context.createTextMessage(txt);
            msg.setJMSCorrelationID("ID:"+user);
            logger.info("CONFIRMATION QUEUE, sending \"" + txt + "\"");
            context.createProducer().send(queueTest, msg);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}