package com.agh.soa;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
public class QueueSender {

    @Resource(mappedName = "java:jboss/exported/jms/queue/SOA_test")
    private Queue queueTest;

    @Inject
    JMSContext context;

    public void sendMessage(String txt) {
        try {
            System.out.println("QUEUE SENDER: sending " + txt);
            context.createProducer().send(queueTest, txt);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}