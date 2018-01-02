package com.github.tinosteinort.flda.fixedlengthstring.writer;

public class ShortAttributeWriter extends NumberAttributeWriter<Short> {

    @Override protected String nullSafeConvertToString(final Short value) {
        return String.valueOf(value);
    }
}
