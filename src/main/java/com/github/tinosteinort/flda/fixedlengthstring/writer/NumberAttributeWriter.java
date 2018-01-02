package com.github.tinosteinort.flda.fixedlengthstring.writer;

import com.github.tinosteinort.flda.accessor.writer.AttributeWriter;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthStringAttribute;
import com.github.tinosteinort.flda.fixedlengthstring.StringUtils;

public abstract class NumberAttributeWriter<T extends Number>
        implements AttributeWriter<FixedLengthString, T, FixedLengthStringAttribute<T>> {

    @Override public void write(final FixedLengthString data, final FixedLengthStringAttribute<T> attribute,
                                final T value) {

        final String newValue = StringUtils.fit(convertToString(value), attribute.getAlignment(),
                attribute.getLength(), attribute.getFiller());

        data.update(attribute.getIndex(), newValue);
    }

    protected String convertToString(final T value) {
        if (value == null) {
            return "";
        }
        return nullSafeConvertToString(value);
    }

    protected abstract String nullSafeConvertToString(final T value);
}
