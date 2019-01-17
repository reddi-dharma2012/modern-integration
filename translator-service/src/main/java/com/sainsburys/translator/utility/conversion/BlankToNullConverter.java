
package com.sainsburys.translator.utility.conversion;

import static com.sainsburys.translator.utility.conversion.Util.*;

public enum BlankToNullConverter implements Converter<String, Object> {
    INSTANCE;

    public static BlankToNullConverter instance() { return INSTANCE; }

    /**
     * @param sourceClass source Class
     * @param destinationClass destination Class
     * @return true if sourceClass is String
     */
    @Override
    public boolean canConvert(Class sourceClass, Class destinationClass) {
        return String.class.equals(sourceClass);
    }

    /**
     * @param source instance of String
     * @return null if source is empty or contains only whitespaces, source otherwise
     */
    @Override
    public Object convert(String source) {
        return blank(source) ? null : source;
    }
}
