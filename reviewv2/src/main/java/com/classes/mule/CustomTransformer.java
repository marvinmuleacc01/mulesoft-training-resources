package com.classes.mule;

import org.mule.api.MuleMessage;
import org.mule.transformer.AbstractMessageTransformer;
 
public class CustomTransformer extends AbstractMessageTransformer {
    private String append;
 
    @Override
    public Object transformMessage(MuleMessage message, String encoding) {
        System.out.println(Thread.currentThread().getName() + ": before transform " + message.getPayload());
        message.setPayload(message.getPayload() + append);
        System.out.println(Thread.currentThread().getName() + ": after transform " + message.getPayload());
        return message;
    }
 
    public String getAppend() {
        return append;
    }
 
    public void setAppend(String append) {
        this.append = append;
    }
}