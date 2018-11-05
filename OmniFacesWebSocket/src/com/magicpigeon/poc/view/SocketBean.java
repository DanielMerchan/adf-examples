package com.magicpigeon.poc.view;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

/**
 * Demo Purpose Managed Bean for illustrating the PushContext WebSocket using OmniFaces
 * @author Daniel Merchan Garcia
 */
@RequestScoped
@Named("socketBean")
public class SocketBean {
    
    @Inject @Push(channel="gooseChannel")
    private PushContext gooseChannel;

    /**
     * Default Constructor
     */
    public SocketBean() {
        super();
        System.out.println("aksjhdiahsdaiu");
    }
    
    public void sendMessage() {
        gooseChannel.send("Hello " + " at " + LocalDateTime.now());
    }
}
