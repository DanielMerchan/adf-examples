package com.magicpigeon.poc.view.cdi;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.annotation.PreDestroy;

import javax.enterprise.context.ApplicationScoped;

import javax.inject.Named;

/**
 * Application Scope - CDI Managed Bean for demo purposes
 * @author Daniel Merchan Garcia
 */
@ApplicationScoped
@Named("applicationCDIBean")
public class ApplicationCDIBean implements Serializable {
    
    /**
     * List of Animals cached in an Application Scope Bean
     */
    private List<String> animals = new ArrayList<>();
    
    /**
     * Constructor
     */
    public ApplicationCDIBean() {
        super();
        animals.add("Goose");
        animals.add("Hedgehog");
        animals.add("Cat");
        animals.add("Dog");
    }
    
    /**
     * Post Construct
     */
    @PostConstruct
    public void init() {
        System.out.println("AppliacationCDIBean initializated");
    }
    
    /**
     * Get the list of animals
     * @return List
     */
    public List<String> getAnimals() {
        return animals;
    }
    
    /**
     * Before to unload the class
     */
    @PreDestroy
    public void preDestroy() {
        System.out.println("AppliacationCDIBean destroyed");
    }
}
