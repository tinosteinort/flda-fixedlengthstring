package com.github.tinosteinort.flda.fixedlengthstring.writer;

import com.github.tinosteinort.flda.accessor.writer.AttributeWriter;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthStringAttribute;
import com.github.tinosteinort.flda.fixedlengthstring.StringUtils;

public class EnumAttributeWriter<T extends Enum<T>>
        implements AttributeWriter<FixedLengthString, T, FixedLengthStringAttribute<T>> {

    @Override public void write(final FixedLengthString data, final FixedLengthStringAttribute<T> attribute,
                                final T value) {

        final String name = (value == null ? null : value.name());
        final String newValue = StringUtils.fit(name, attribute.getAlignment(), attribute.getLength(),
                attribute.getFiller());

        data.update(attribute.getIndex(), newValue);
    }
}
