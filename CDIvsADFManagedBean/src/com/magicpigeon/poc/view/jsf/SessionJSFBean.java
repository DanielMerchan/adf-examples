package com.magicpigeon.poc.view.jsf;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

public class SessionJSFBean implements Serializable {
    
    /**
     * List of Animals cached in an Application Scope Bean
     */
    private List<String> persons = new ArrayList<>();
    
    /**
     * Constructor
     */
    public SessionJSFBean() {
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
        System.out.println("SessionJSFBean initializated");
    }
    
    /**
     * Get the list of animals
     * @return List
     */
    public List<String> getPersons() {
        return persons;
    }
}
