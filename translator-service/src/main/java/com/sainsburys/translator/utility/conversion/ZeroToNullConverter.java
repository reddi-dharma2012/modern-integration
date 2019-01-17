
package com.sainsburys.translator.utility.conversion;

public enum ZeroToNullConverter implements Converter<Number, Object> {
    INSTANCE;

    public static ZeroToNullConverter instance() { return INSTANCE; }

    /**
     * @param sourceClass source Class
     * @param destinationClass destination Class
     * @return true if sourceClass is a subclass of Number
     */
    @Override
    public boolean canConvert(Class sourceClass, Class destinationClass) {
        return Number.class.isAssignableFrom(sourceClass);
    }

    /**
     * @param source instance of String
     * @return null if source.intValue() is zero, source otherwise
     */
    @Override
    public Object convert(Number source) {
        return source.intValue() == 0 ? null : source;
    }
}
