package com.classes.mule;

import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.context.MuleContextBuilder;
import org.mule.api.context.MuleContextFactory;
import org.mule.config.DefaultMuleConfiguration;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.context.DefaultMuleContextBuilder;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.module.client.MuleClient;
 
public class MuleMessageExchangeExample {
    public static void main(String[] args) throws Exception {
        DefaultMuleConfiguration dmc = new DefaultMuleConfiguration();
        dmc.setId("muleexample");
        dmc.setWorkingDirectory("/esb/mule");
        SpringXmlConfigurationBuilder configBuilder = new SpringXmlConfigurationBuilder(
                "mulemessageexchangepattern.xml");
        MuleContextBuilder contextBuilder = new DefaultMuleContextBuilder();
        contextBuilder.setMuleConfiguration(dmc);
        MuleContextFactory contextFactory = new DefaultMuleContextFactory();
        MuleContext ctx = contextFactory.createMuleContext(configBuilder,
                contextBuilder);
        ctx.start();
        try {
            MuleClient muleClient = new MuleClient(ctx);
            String payload = "XYZ-";
            MuleMessage message = muleClient.send("vm://in1", payload, null);
            System.out.println("Response: " + message.getPayloadAsString());
            
            message = muleClient.send("vm://in2", payload, null);
            System.out.println("Response: " + message.getPayloadAsString());
            
            message = muleClient.send("vm://in3", payload, null);
            System.out.println("Response: " + message.getPayloadAsString());
            
            message = muleClient.send("vm://in4", payload, null);
            System.out.println("Response: " + message.getPayloadAsString());
        } finally {
            ctx.dispose();
        }
    }
}
