package com.mastertheboss.quarkus.jms;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ApplicationScoped
public class JMSProducer {

	private static final Logger LOG = LoggerFactory.getLogger(JMSProducer.class);
	
    @Inject
    ConnectionFactory connectionFactory;

    public void sendMessage(String message) {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)){
            context.createProducer().send(context.createQueue("colaexemplo"), message);
        } catch (JMSRuntimeException ex) {
        	LOG.info(ex.getMessage() + "\n");
        }
    }
}
