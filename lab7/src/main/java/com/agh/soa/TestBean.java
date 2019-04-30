package com.agh.soa;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("TestBean")
public class TestBean implements Serializable {

   @Inject
   QueueSender sender;

    public String getTitle() {
        return title;
    }

    private String title = "Queue";

    public void testMethod(){
        System.out.println("in bean, sending message");
        sender.sendMessage("Henlo");
    }
}
