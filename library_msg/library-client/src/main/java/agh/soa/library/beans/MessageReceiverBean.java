package agh.soa.library.beans;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.io.Serializable;
import java.util.logging.Logger;

public class MessageReceiverBean implements Serializable {
    @Resource(mappedName = "java:jboss/exported/jms/topic/SOA_Test")
    private Queue myQueue;
    @Inject
    private JMSContext context;

    private static final Logger logger = Logger.getLogger(MessageReceiverBean.class.getName());

    public MessageReceiverBean() { }

    public void receiveMessage() {
        String message = context
                .createConsumer(myQueue)
                .receiveBody(String.class, 1000);
        if (message == null)
            message = "Nic nie ma w kolejce";
        logger.info(message);
    }
}
