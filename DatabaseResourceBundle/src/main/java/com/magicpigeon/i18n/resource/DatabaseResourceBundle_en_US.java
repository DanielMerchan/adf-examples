package com.magicpigeon.i18n.resource;

import java.util.Locale;

/**
 * Resource Bundle for USA - English
 * @author magicpigeon
 */
public final class DatabaseResourceBundle_en_US extends DatabaseResourceBundle {
    
    /**
     * Default Constructor
     */
    public DatabaseResourceBundle_en_US() {
        super();
        System.out.println("SEMEN EN US");
    }
    
    /**
     * Locale of the Resource Bundle
     * @return Locale
     */
    @Override
    public Locale getLocale() {
        return Locale.US;           
    }
}
