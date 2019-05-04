package com.agh.soa;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Named(value = "queueReceiver")
@Dependent
public class QueueReceiver {
    @Inject
    private JMSContext context;
    @Resource(mappedName = "java:jboss/exported/jms/queue/SOA_test")
    Queue myQueue;

    public void receiveMessage() {
        String message = null;
        try {
            message = context.createConsumer(myQueue).receiveBody(String.class);
        }catch (Exception exc){
            exc.printStackTrace();
        }
        if(message == null) {
            message = "queue empty";
        }

        System.out.println("QUEUE RECEIVER: got \"" + message +"\"");


    }
}
