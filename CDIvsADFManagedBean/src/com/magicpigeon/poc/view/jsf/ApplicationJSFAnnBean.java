package com.magicpigeon.poc.view.jsf;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Application Scope - JSF Annotated Managed Bean for demo purposes
 * @author Daniel Merchan Garcia
 */
@ManagedBean(name="applicationJSFAnnBean")
@ApplicationScoped
public class ApplicationJSFAnnBean implements Serializable {
    
    /**
     * List of Animals cached in an Application Scope Bean
     */
    private List<String> colors = new ArrayList<>();
    
    /**
     * Default Constructor
     */
    public ApplicationJSFAnnBean() {
        super();
        colors.add("Blue");
        colors.add("Red");
        colors.add("White");
    }
    
    /**
     * Post Construct
     */
    @PostConstruct
    public void init() {
        System.out.println("ApplicationJSFAnn Bean initializated");   
    }
    
    /**
     * Get the cached colors
     * @return List
     */
    public List<String> getColors() {
        return colors;
    }
}
