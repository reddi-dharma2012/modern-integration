
package com.sainsburys.translator.utility.conversion;


public abstract class ConverterAdapter<S, T> implements Converter<S, T> {

    @Override
    public boolean canConvert(Class aSourceClass, Class aDestinationClass) {
        return sourceClass().isAssignableFrom(aSourceClass) && destinationClass().isAssignableFrom(aDestinationClass);
    }

    protected abstract Class<S> sourceClass();

    protected abstract Class<T> destinationClass();

    @Override
    public T convert(S source) {
        try {
            return doConvert(source);
        } catch (Exception e) {
            throw new ConversionException(e);
        }
    }

    /**
     * Converts instance of <tt>S</tt> to <tt>T</tt>.
     * @param source instance of S, can be null
     * @return instance of S converted to type T
     */
    protected abstract T doConvert(S source) throws Exception;
}
