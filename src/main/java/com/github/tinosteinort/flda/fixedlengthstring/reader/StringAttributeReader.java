package com.github.tinosteinort.flda.fixedlengthstring.reader;

import com.github.tinosteinort.flda.accessor.AttributeReader;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthStringAttribute;
import com.github.tinosteinort.flda.fixedlengthstring.StringUtils;

public class StringAttributeReader
        implements AttributeReader<FixedLengthString, String, FixedLengthStringAttribute<String>> {

    @Override public String read(final FixedLengthString data, final FixedLengthStringAttribute<String> attribute) {
        final String value = StringUtils.readAndTrim(data, attribute.getIndex(), attribute.getLength(),
                attribute.getFiller());
        if (value.isEmpty()) {
            return null;
        }
        return value;
    }
}
