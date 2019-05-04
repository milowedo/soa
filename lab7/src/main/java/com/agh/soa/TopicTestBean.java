package com.agh.soa;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("TopicTestBean")
public class TopicTestBean implements Serializable {

   @Inject
   TopicSender sender;

   @Inject
   TopicReceiver receiver;

    public String getTitle() {
        return title;
    }

    private String title = "Topic(received automatically)";

    public void testMethod(){
        receiver.subscribe();
        String msg = "Hello from the topic side";
        System.out.println("in TopicTestBean, sending message \"" +msg+"\"");
        sender.sendMessage(msg);
        receiver.receiveMessage();
    }
}
