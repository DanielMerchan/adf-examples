package com.magicpigeon.i18n.resource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListResourceBundle;

import java.util.Locale;

import java.util.Map;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.uicli.binding.JUCtrlActionBinding;

/**
 * Extension to ListResourceBundle for loading the text from Database
 * @author magicpigeon
 */
public class DatabaseResourceBundle extends ListResourceBundle {
    
    /**
     * Keeps the labels for the specific locale (default locale)
     */
    private Object[][] resourceBundle;
    
    /**
     * Logging
     */
    private static final ADFLogger LOG = ADFLogger.createADFLogger(DatabaseResourceBundle.class);
    
    /**
     * Class Name
     */
    private static final String CLASS_NAME = DatabaseResourceBundle.class.getName();

    /**
     * 
     */
    protected static final String GENERIC_PAGE_DEF = "com_magicpigeon_i18n_genericPagePageDef";
    
    /**
     * Default Constructor
     */
    public DatabaseResourceBundle() {
        super();
    }
    
    /**
     * Retrieve the text from the database
     * @return Object[][] - Resource Bundle
     */
    @Override
    protected Object[][] getContents() {
        Locale locale = this.getLocale();
        if (resourceBundle == null) {
            initResourceBundle(locale);
        } else {
            LOG.warning(CLASS_NAME, "getContents", "Resource Bundle has been loaded already (Concurrency)");
        }
        return resourceBundle;
    }

    /**
     * Auxiliar method which loads the Resource Bundle from the Database by accessing via ADF Model
     * @param locale - Locale to load the text
     */
    private synchronized void initResourceBundle(Locale locale) {
        final String METHOD_NAME = "initResourceBundle";
        LOG.entering(CLASS_NAME, METHOD_NAME);
        LOG.info(CLASS_NAME, "initResourceBundle", "Loading resource bundle for: " + locale.toString());
        Map<String,String> map = new HashMap<String,String>();
        map = getResourceBundle(locale.toString());
        // copy all values from map in object array
        if (map.size() == 0) {
            resourceBundle = new Object[0][0];
        } else {
            Object[][] returnValue = new Object[map.size()][2];
            int i = 0;
            for (Iterator keyIter = map.keySet().iterator(); keyIter.hasNext();
            ) {
                Object key = keyIter.next();
                returnValue[i][0] = key;
                returnValue[i][1] = map.get(key);
                i++;
            }
            resourceBundle = returnValue;
        }
        LOG.exiting(CLASS_NAME, METHOD_NAME);
    }

    /**
     * Auxiliar method which loads the Resource Bundle from the Database
     * @param localeStr - Current Locale
     * @return Map - Contains the text of the resource bundle
     */
    @SuppressWarnings("unchecked")
    private Map<String,String> getResourceBundle(String localeStr) {
        Map<String,String> map = new HashMap<String,String>();
        BindingContext bindingContext = BindingContext.getCurrent();
        if (bindingContext != null) {
            DCBindingContainer container = bindingContext.findBindingContainer(GENERIC_PAGE_DEF);
            if (container != null) {
                JUCtrlActionBinding getResourceBundleBinding = (JUCtrlActionBinding)container.findCtrlBinding("getResourceBundle");
                getResourceBundleBinding.getParamsMap().put("localeStr", localeStr);
                Object result = getResourceBundleBinding.execute();
                if (result instanceof Map) {
                    map = (Map<String,String>)result;
                }
            }
        } else {
            LOG.warning("BindingContext cannot be loaded, JDeveloper can be in Design Time, do not worry, be happy :)");
        }
        return map;
    }

    /**
     * Default locale
     * @return Locale - Default Locale
     */
    @Override
    public Locale getLocale() {
        return Locale.ENGLISH;
    }
}
