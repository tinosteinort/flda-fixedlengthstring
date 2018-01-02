package com.github.tinosteinort.flda.fixedlengthstring.reader;

import com.github.tinosteinort.flda.accessor.reader.AttributeReader;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthStringAttribute;
import com.github.tinosteinort.flda.fixedlengthstring.StringUtils;

public abstract class NumberAttributeReader<T extends Number>
        implements AttributeReader<FixedLengthString, T, FixedLengthStringAttribute<T>> {

    @Override public T read(final FixedLengthString data, final FixedLengthStringAttribute<T> attribute) {
        final String value = StringUtils.readAndTrim(data, attribute.getIndex(), attribute.getLength(),
                attribute.getFiller());
        return convertToNumber(value);
    }

    protected T convertToNumber(final String value) {
        if (value.equals("")) {
            return null;
        }
        return nullSafeConvertToNumber(value);
    }

    protected abstract T nullSafeConvertToNumber(final String value);
}
