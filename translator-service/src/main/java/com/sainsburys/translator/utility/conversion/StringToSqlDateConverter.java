
package com.sainsburys.translator.utility.conversion;

import static com.sainsburys.translator.utility.conversion.Util.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToSqlDateConverter extends ConverterAdapter<String, java.sql.Date> {

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
    public StringToSqlDateConverter(String pattern) {
        this(new SimpleDateFormat(pattern));
    }
    /**
     * @param format DateFormat to use for conversion
     */
    public StringToSqlDateConverter(DateFormat format) {
        this.format = format;
    }

    @Override protected Class<String> sourceClass() { return String.class; }

    @Override protected Class<java.sql.Date> destinationClass() { return java.sql.Date.class; }

    /**
     * @param source instance of String or null
     * @return source converted to java.sql.Date, or null if source is blank
     * @throws ParseException if conversion failed
     */
    @Override
    public java.sql.Date doConvert(String source) throws ParseException {
        return blank(source) ? null : new java.sql.Date(threadLocalFormat.get().parse(source).getTime());
    }
}
