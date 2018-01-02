package com.github.tinosteinort.flda.fixedlengthstring.writer;

import com.github.tinosteinort.flda.fixedlengthstring.Alignment;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnumAttributeWriterTest {

    private final EnumAttributeWriter<Colour> enumWriter = new EnumAttributeWriter<>();

    @Test public void writeValue() {
        final FixedLengthString data = new FixedLengthString(7, ' ');
        final FixedLengthStringAttribute<Colour> attribute = new FixedLengthStringAttribute<>(Colour.class, 0, 7, Alignment.LEFT);
        enumWriter.write(data, attribute, Colour.RED);
        assertEquals("RED    ", data.toString());
    }

    @Test public void writeNull() {
        final FixedLengthString data = new FixedLengthString(7, ' ');
        final FixedLengthStringAttribute<Colour> attribute = new FixedLengthStringAttribute<>(Colour.class, 0, 7, Alignment.LEFT);
        enumWriter.write(data, attribute, null);
        assertEquals("       ", data.toString());
    }

    @Test public void writeValueRight() {
        final FixedLengthString data = new FixedLengthString(7, ' ');
        final FixedLengthStringAttribute<Colour> attribute = new FixedLengthStringAttribute<>(Colour.class, 0, 7, Alignment.RIGHT);
        enumWriter.write(data, attribute, Colour.BLUE);
        assertEquals("   BLUE", data.toString());
    }
}

enum Colour {

    RED,
    BLUE
}