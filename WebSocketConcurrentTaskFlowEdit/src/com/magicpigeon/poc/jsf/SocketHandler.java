package com.magicpigeon.poc.jsf;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.render.ClientEvent;

/**
 * Managed Bean to handle messages send from the server
 * @author Daniel Merchan Garcia
 */
public final class SocketHandler {
    
    /**
     * Displays the Faces Message of the user editing the Task Flow
     * @param ce
     */
    public void SocketMessageReceive(ClientEvent ce) {
        String userId = (String) ce.getParameters().get("userId");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Take Care!",
                                "User: " + userId + " is editing this Task Flow, take care, he will override your changes and run away with your money!"));
    }
}
