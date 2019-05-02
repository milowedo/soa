package com.agh.soa;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.*;

public class QueueSender {

    @Resource(mappedName = "java:jboss/exported/jms/topic/SOA_Test")
    private Topic queueTest;

    @Inject
    JMSContext context;

    public void sendMessage(String txt) {
        try {
            System.out.println("QUEUE SENDER: sending \"" + txt+"\"");
            context.createProducer().send(queueTest, txt);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}