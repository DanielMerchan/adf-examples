package com.magicpigeon.i18n.resource;

import java.util.Locale;

/**
 * Resource Bundle for United Kingdom - English
 * @author magicpigeon
 */
public final class DatabaseResourceBundle_en_GB extends DatabaseResourceBundle {
    
    /**
     * Default Constructor
     */
    public DatabaseResourceBundle_en_GB() {
        super();
        System.out.println("SEMEN EN GB");
    }
    
    /**
     * Locale of the Resource Bundle
     * @return
     */
    @Override
    public Locale getLocale() {
        return Locale.UK;
    }
}
