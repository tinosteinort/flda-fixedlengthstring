package com.github.tinosteinort.flda.fixedlengthstring.writer;

import com.github.tinosteinort.flda.accessor.AttributeWriter;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthStringAttribute;
import com.github.tinosteinort.flda.fixedlengthstring.StringUtils;

public class StringAttributeWriter
        implements AttributeWriter<FixedLengthString, String, FixedLengthStringAttribute<String>> {

    @Override public void write(final FixedLengthString data, final FixedLengthStringAttribute<String> attribute,
                                final String value) {

        final String newValue = StringUtils.fit(value, attribute.getAlignment(), attribute.getLength(),
                attribute.getFiller());

        data.update(attribute.getIndex(), newValue);
    }
}
