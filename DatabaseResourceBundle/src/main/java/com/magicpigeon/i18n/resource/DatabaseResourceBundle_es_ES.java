package com.magicpigeon.i18n.resource;

import java.util.Locale;

/**
 * Resource Bundle for Spain - Spanish
 * @author magicpigeon
 */
public final class DatabaseResourceBundle_es_ES extends DatabaseResourceBundle {
    
    /**
     * Default Constructor
     */
    public DatabaseResourceBundle_es_ES() {
        super();
        System.out.println("SEMEN ES ES");
    }
    
    /**
     * Locale of the Resource Bundle
     * @return Locale
     */
    @Override
    public Locale getLocale() {
        return new Locale("es","ES");
    }
}
