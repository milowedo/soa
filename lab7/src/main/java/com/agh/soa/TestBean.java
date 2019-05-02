package com.agh.soa;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("TestBean")
public class TestBean implements Serializable {

   @Inject
   QueueSender sender;

   @Inject
   QueueReceiver receiver;

    public String getTitle() {
        return title;
    }

    private String title = "Queue";

    public void testMethod(){
        receiver.subscribe();
        String msg = "Hello from the topic side";
        System.out.println("in TestBean, sending message \"" +msg+"\"");
        sender.sendMessage(msg);
        receiver.receiveMessage();
    }
}
