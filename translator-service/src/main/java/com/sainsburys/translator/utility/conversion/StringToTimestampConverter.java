
package com.sainsburys.translator.utility.conversion;

import static com.sainsburys.translator.utility.conversion.Util.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class StringToTimestampConverter extends ConverterAdapter<String, java.sql.Timestamp> {

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
    public StringToTimestampConverter(String pattern) {
        this(new SimpleDateFormat(pattern));
    }
    /**
     * @param format DateFormat to use for conversion
     */
    public StringToTimestampConverter(DateFormat format) {
        this.format = format;
    }

    @Override protected Class<String> sourceClass() { return String.class; }

    @Override protected Class<java.sql.Timestamp> destinationClass() { return java.sql.Timestamp.class; }

    /**
     * @param source instance of String or null
     * @return source converted to java.sql.Timestamp, or null if source is blank
     * @throws ParseException if conversion failed
     */
    @Override
    public java.sql.Timestamp doConvert(String source) throws ParseException {
        return blank(source) ? null : new java.sql.Timestamp(threadLocalFormat.get().parse(source).getTime());
    }
}
