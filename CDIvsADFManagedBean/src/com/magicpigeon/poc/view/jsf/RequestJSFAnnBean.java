package com.magicpigeon.poc.view.jsf;

import com.magicpigeon.poc.view.cdi.ApplicationCDIBean;

import com.magicpigeon.poc.view.cdi.SessionCDIBean;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import javax.inject.Inject;

/**
 * This is a JSF Request Scope Managed Bean registered using annotations
 * @author Daniel Merchan Garcia
 */
@ManagedBean(name="requestJSFAnnBean")
@RequestScoped
public class RequestJSFAnnBean {
    
    @ManagedProperty(value="#{applicationJSFBean}")
    private ApplicationJSFBean appJSFBean;
    
    @ManagedProperty(value="#{applicationJSFAnnBean}")
    private ApplicationJSFAnnBean appJSFAnnBean;
    
    @Inject
    private SessionCDIBean sessionCDIBean;
    
    @Inject
    private ApplicationCDIBean appCDIBean;
    
    /**
     * Default Constructor
     */
    public RequestJSFAnnBean() {
        super();
    }
    
    /**
     * Post Construct
     */
    @PostConstruct
    public void init() {
        System.out.println("RequestJSFAnn Bean has been initiated");
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

    public void setAppJSFAnnBean(ApplicationJSFAnnBean appJSFAnnBean) {
        this.appJSFAnnBean = appJSFAnnBean;
    }

    public ApplicationJSFAnnBean getAppJSFAnnBean() {
        return appJSFAnnBean;
    }

    public void setAppJSFBean(ApplicationJSFBean appJSFBean) {
        this.appJSFBean = appJSFBean;
    }

    public ApplicationJSFBean getAppJSFBean() {
        return appJSFBean;
    }
}
