package agh.soa.library.beans;

import com.agh.soa.entity.Reader;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.io.Serializable;
import java.util.logging.Logger;

@Named(value = "confirmationQueueConsumer")
@Dependent
public class ConfirmationQueueConsumer implements Serializable {
    @Resource(mappedName = "java:jboss/exported/jms/queue/SOA_test")
    private Queue myQueue;
    @Inject
    private JMSContext context;

    private static final Logger logger = Logger.getLogger(ConfirmationQueueConsumer.class.getName());

    public void getMessage(Reader r) {
        String message = null;
        try {
            message = context
                    .createConsumer(myQueue, "JMSCorrelationID ='" + "ID:" + r.getName() + r.getSurname() + "'")
                    .receiveBody(String.class, 1000);
        }catch (Exception exc){
            exc.printStackTrace();
        }
        if (message == null)
            message = "No new messages";
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "", message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        logger.info("CONFIRMATION QUEUE, reading: " + message);
    }
}
