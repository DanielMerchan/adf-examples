package com.magicpigeon.i18n.resource;

import java.util.Locale;

/**
 * Resource Bundle for Spanish
 * @author magicpigeon
 */
public final class DatabaseResourceBundle_es extends DatabaseResourceBundle {
    
    /**
     * Default Constructor
     */
    public DatabaseResourceBundle_es() {
        super();
        System.out.println("SEMEN ES");
    }

    /**
     * Locale of the Resource Bundle
     * @return Locale
     */
    @Override
    public Locale getLocale() {
        return new Locale("es");
    }
}
