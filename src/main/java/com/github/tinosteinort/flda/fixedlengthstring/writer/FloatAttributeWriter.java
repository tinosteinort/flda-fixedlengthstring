package com.github.tinosteinort.flda.fixedlengthstring.writer;

public class FloatAttributeWriter extends NumberAttributeWriter<Float> {

    @Override protected String nullSafeConvertToString(final Float value) {
        return String.valueOf(value);
    }
}
