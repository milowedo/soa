package com.agh.soa;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;

public class QueueReceiver {
    @Inject
    private JMSContext context;
    @Resource(mappedName = "java:jboss/exported/jms/topic/SOA_Test")
    Topic topic;

    private JMSConsumer consumer;



    public void subscribe() {
        this.consumer = this.
                context.
                createConsumer(topic);
    }

    public void receiveMessage() {
        try {
            System.out.println("QUEUE RECEIVER: got \"" + this.consumer.receiveBody(String.class)+"\"");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}