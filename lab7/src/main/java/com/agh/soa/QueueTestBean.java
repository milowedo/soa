package com.agh.soa;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("QueueTestBean")
public class QueueTestBean implements Serializable {

    @Inject
    QueueSender sender;

    public String getTitle() {
        return title;
    }

    private String title = "Queue";

    public void testMethod(){
        sender.sendMessage("Henlo to the queue");
    }
}
