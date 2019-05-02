package agh.soa.library.beans;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;
import java.io.Serializable;

public class AvailabilityTopic implements Serializable {
    @Resource(mappedName = "java:jboss/exported/jms/topic/SOA_Test")
    private Topic topic;
    @Inject
    private JMSContext jmsContext;
}
