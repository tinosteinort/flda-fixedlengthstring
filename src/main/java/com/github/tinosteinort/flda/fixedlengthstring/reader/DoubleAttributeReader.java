package com.github.tinosteinort.flda.fixedlengthstring.reader;

public class DoubleAttributeReader extends NumberAttributeReader<Double> {

    @Override protected Double nullSafeConvertToNumber(final String value) {
        return Double.valueOf(value);
    }
}
