
package com.sainsburys.translator.utility.conversion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateToStringConverter implements Converter<java.util.Date, String> {

    private final DateFormat format;
    // Calendar and DateFormat are not thread safe: http://www.javacodegeeks.com/2010/07/java-best-practices-dateformat-in.html
    private final ThreadLocal<DateFormat> threadLocalFormat = new ThreadLocal<DateFormat>() {
        @Override protected DateFormat initialValue() {
            return (DateFormat) format.clone();
        }
    };

    /**
     * @param pattern pattern to use for conversion
     */
    public DateToStringConverter(String pattern) {
        this(new SimpleDateFormat(pattern));
    }
    /**
     * @param format DateFormat to use for conversion
     */
    public DateToStringConverter(DateFormat format) {
        this.format = format;
    }

    /**
     * @param sourceClass source Class
     * @param destinationClass destination Class
     * @return true if sourceClass is java.util.Date, or a subclass of it, and destinationClass is String
     */
    @Override
    public boolean canConvert(Class sourceClass, Class destinationClass) {
        return java.util.Date.class.isAssignableFrom(sourceClass) && String.class.equals(destinationClass);
    }

    /**
     * @param source instance of java.util.Date, or subclasses of it, for example:
     * java.sql.Date, java.sql.Time, java.sql.Timestamp
     * @return source converted to String
     */
    @Override
    public String convert(java.util.Date source) {
        return threadLocalFormat.get().format(source);
    }
}
