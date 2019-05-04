package com.agh.soa;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.*;

public class TopicSender {

    @Resource(mappedName = "java:jboss/exported/jms/topic/SOA_Test")
    private Topic topic;

    @Inject
    JMSContext context;

    public void sendMessage(String txt) {
        try {
            System.out.println("TOPIC SENDER: sending \"" + txt+"\"");
            context.createProducer().send(topic, txt);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}