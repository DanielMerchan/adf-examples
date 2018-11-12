package com.magicpigeon.poc.view.cdi;

import com.magicpigeon.poc.view.jsf.ApplicationJSFAnnBean;

import com.magicpigeon.poc.view.jsf.ApplicationJSFBean;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.annotation.PreDestroy;

import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Request Scope - CDI Managed Bean for demo purposes
 * @author Daniel Merchan Garcia
 */
@RequestScoped
@Named("requestCDIBean")
public class RequestCDIBean {
    
    /**
     * Inject the Application Scope CDI Bean
     */
    @Inject
    private ApplicationCDIBean appCDIBean;
    
    /**
     * Inject Application Scope JSF Annotated Bean
     */
    @Inject
    private ApplicationJSFAnnBean appJSFAnnBean;
    
    /**
     * Inject Application Scope JSF Bean of adfc-config.xml
     */
    @Inject 
    private ApplicationJSFBean appJSFBean;
    
    /**
     * Inject Session Scope CDI Bean
     */
    @Inject 
    private SessionCDIBean sessionCDIBean;
    
    /**
     * Default Constructor
     */
    public RequestCDIBean() {
        super();
    }
    
    /**
     * Post Construct Method
     */
    @PostConstruct
    public void init() {
        System.out.println("Request Scoped CDI Bean initializated");
    }
    
    /**
     *  Demo Action writing in the standard output
     */
    public void demoAction() {
        System.out.println("Requested");
        List<String> animals = appCDIBean.getAnimals();
        animals.forEach(x -> System.out.println(x));
               
        List<String> colors = appJSFAnnBean.getColors();
        colors.forEach(x -> System.out.println(x));
        
        List<String> colors2 = appJSFBean.getColors();
        colors2.forEach(x -> System.out.println(x));
        
        List<String> persons = sessionCDIBean.getPersons();
        persons.forEach(x -> System.out.println(x));
    }
    
    /**
     * Before to unload the class
     */
    @PreDestroy
    public void preDestroy() {
        System.out.println("Request Scoped CDI Bean destroyed");
    }
}
