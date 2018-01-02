package com.github.tinosteinort.flda.fixedlengthstring.reader;

public class LongAttributeReader extends NumberAttributeReader<Long> {

    @Override protected Long nullSafeConvertToNumber(final String value) {
        return Long.valueOf(value);
    }
}
