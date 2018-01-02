package com.github.tinosteinort.flda.fixedlengthstring.writer;

import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegerAttributeWriterTest {

    private final IntegerAttributeWriter writer = new IntegerAttributeWriter();

    @Test public void writeMin() {
        final FixedLengthString data = new FixedLengthString(11, ' ');
        final FixedLengthStringAttribute<Integer> attribute = new FixedLengthStringAttribute<>(Integer.class, 0, 11);
        writer.write(data, attribute, -2147483648);
        assertEquals("-2147483648", data.toString());
    }

    @Test public void writeMax() {
        final FixedLengthString data = new FixedLengthString(10, ' ');
        final FixedLengthStringAttribute<Integer> attribute = new FixedLengthStringAttribute<>(Integer.class, 0, 10);
        writer.write(data, attribute, 2147483647);
        assertEquals("2147483647", data.toString());
    }

    @Test public void writeNull() {
        final FixedLengthString data = new FixedLengthString(10, ' ');
        final FixedLengthStringAttribute<Integer> attribute = new FixedLengthStringAttribute<>(Integer.class, 0, 10);
        writer.write(data, attribute, null);
        assertEquals("          ", data.toString());
    }
}
