package com.magicpigeon.poc.view.jsf;

import com.magicpigeon.poc.view.cdi.ApplicationCDIBean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.omnifaces.util.Beans;

/**
 * This is a JSF Request Scope Managed Bean which is registered in adfc-config.xml
 * @author Daniel Merchan Garcia
 */
public class RequestJSFBean {
    
    // *********************
    
    // Test 1 YOU CANNOT @INJECT A CDI BEAN IN A JSF BEAN!!!!! 
    // IT DOES NOT WORK
    
//    /**
//     * Inject the Application Scope CDI Bean
//     */
//    @Inject
//    private ApplicationCDIBean appCDIBean;
//    
//    /**
//     * Inject Application Scope JSF Annotated Bean
//     */
//    @Inject
//    private ApplicationJSFAnnBean appJSFBean;
    
    // *********************
    
    // Test 2. JSF 2.2 ManagedPropery is not CDI compatible. In JSF 2.3 there is a compatible ManagedProperty CDI Annotation
    // IT DOES NOT WORK
//    @ManagedProperty(value="#{applicationCDIBean}")
//    private ApplicationCDIBean appCDIBean;
    
    // Test 3. Do not mix registration in XML and annotations!!! ;)
    private SessionJSFBean sessionJSFBean;
    
    // Inject by using OmniFaces
    private ApplicationCDIBean appCDIBean;
    
    /**
     * Constructor
     */
    public RequestJSFBean() {
        super();
    }
    
    /**
     * Post Construct which ensures the context-dependency injection is ready.
     */
    @PostConstruct
    public void init() {
        System.out.println("RequestJSFBean initiated");        
        // Get the CDI Managed Beans by using OmniFaces 2.7
        appCDIBean = Beans.getReference(ApplicationCDIBean.class);
    }

    /**
     *  Demo Action writing in the standard output
     */
    public void demoAction() {
        System.out.println("Requested");
        
        List<String> animals = appCDIBean.getAnimals();
        animals.forEach(x -> System.out.println(x));
//               
//        List<String> colors = appJSFAnnBean.getColors();
//        colors.forEach(x -> System.out.println(x));
        
//        List<String> colors2 = appJSFBean.getColors();
//        colors2.forEach(x -> System.out.println(x));
        
        List<String> persons = sessionJSFBean.getPersons();
        persons.forEach(x -> System.out.println(x));
    }


    public void setSessionJSFBean(SessionJSFBean sessionJSFBean) {
        this.sessionJSFBean = sessionJSFBean;
    }

    public SessionJSFBean getSessionJSFBean() {
        return sessionJSFBean;
    }

}
