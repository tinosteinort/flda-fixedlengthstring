package com.github.tinosteinort.flda.fixedlengthstring.writer;

import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringAttributeWriterTest {

    private static final FixedLengthStringAttribute<String> STRING_ONE = new FixedLengthStringAttribute<>(String.class, 0, 3);
    private static final FixedLengthStringAttribute<String> STRING_TWO = new FixedLengthStringAttribute<>(String.class, 3, 3);
    private static final FixedLengthStringAttribute<String> STRING_THREE = new FixedLengthStringAttribute<>(String.class, 6, 4);

    private final StringAttributeWriter writer = new StringAttributeWriter();

    @Test public void writeOne() {
        final FixedLengthString data = new FixedLengthString("          ");
        writer.write(data, STRING_ONE, "123");
        assertEquals("123       ", data.toString());
    }

    @Test public void writeTwo() {
        final FixedLengthString data = new FixedLengthString("          ");
        writer.write(data, STRING_TWO, "-#-");
        assertEquals("   -#-    ", data.toString());
    }

    @Test public void writeThree() {
        final FixedLengthString data = new FixedLengthString("          ");
        writer.write(data, STRING_THREE, "ABCd");
        assertEquals("      ABCd", data.toString());
    }

    @Test public void writeNull() {
        final FixedLengthString data = new FixedLengthString("          ");
        writer.write(data, STRING_ONE, null);
        assertEquals("          ", data.toString());
    }
}
