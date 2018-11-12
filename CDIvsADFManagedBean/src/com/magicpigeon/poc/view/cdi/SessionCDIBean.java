package com.magicpigeon.poc.view.cdi;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.annotation.PreDestroy;

import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

/**
 * Application Scope - CDI Managed Bean for demo purposes
 * @author Daniel Merchan Garcia
 */
@SessionScoped
@Named("sessionCDIBean")
public class SessionCDIBean implements Serializable {
    
    /**
     * List of Animals cached in an Application Scope Bean
     */
    private List<String> persons = new ArrayList<>();
    
    /**
     * Constructor
     */
    public SessionCDIBean() {
        super();
        persons.add("Daniel");
        persons.add("Pedro");
        persons.add("Eva");
        persons.add("Figuras");
    }
    
    /**
     * Post Construct
     */
    @PostConstruct
    public void init() {
        System.out.println("SessionCDIBean initializated");
    }
    
    /**
     * Get the list of animals
     * @return List
     */
    public List<String> getPersons() {
        return persons;
    }
    
    /**
     * Before to unload the class
     */
    @PreDestroy
    public void preDestroy() {
        System.out.println("SessionCDIBean destroyed");
    }
}
