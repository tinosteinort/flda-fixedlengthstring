package com.github.tinosteinort.flda.fixedlengthstring;

import com.github.tinosteinort.flda.accessor.AccessorConfigBuilder;
import com.github.tinosteinort.flda.fixedlengthstring.reader.BigDecimalAttributeReader;
import com.github.tinosteinort.flda.fixedlengthstring.reader.BigIntegerAttributeReader;
import com.github.tinosteinort.flda.fixedlengthstring.reader.ByteAttributeReader;
import com.github.tinosteinort.flda.fixedlengthstring.reader.DoubleAttributeReader;
import com.github.tinosteinort.flda.fixedlengthstring.reader.FloatAttributeReader;
import com.github.tinosteinort.flda.fixedlengthstring.reader.IntegerAttributeReader;
import com.github.tinosteinort.flda.fixedlengthstring.reader.LongAttributeReader;
import com.github.tinosteinort.flda.fixedlengthstring.reader.ShortAttributeReader;
import com.github.tinosteinort.flda.fixedlengthstring.reader.StringAttributeReader;
import com.github.tinosteinort.flda.fixedlengthstring.writer.BigDecimalAttributeWriter;
import com.github.tinosteinort.flda.fixedlengthstring.writer.BigIntegerAttributeWriter;
import com.github.tinosteinort.flda.fixedlengthstring.writer.ByteAttributeWriter;
import com.github.tinosteinort.flda.fixedlengthstring.writer.DoubleAttributeWriter;
import com.github.tinosteinort.flda.fixedlengthstring.writer.FloatAttributeWriter;
import com.github.tinosteinort.flda.fixedlengthstring.writer.IntegerAttributeWriter;
import com.github.tinosteinort.flda.fixedlengthstring.writer.LongAttributeWriter;
import com.github.tinosteinort.flda.fixedlengthstring.writer.ShortAttributeWriter;
import com.github.tinosteinort.flda.fixedlengthstring.writer.StringAttributeWriter;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Default AccessorConfigBuilder for {@link FixedLengthStringAttribute}. Readers and writers for
 *  types
 *  <ul>
 *      <li>String</li>
 *      <li>Byte</li>
 *      <li>Short</li>
 *      <li>Integer</li>
 *      <li>Long</li>
 *      <li>Float</li>
 *      <li>Double</li>
 *      <li>BigInteger</li>
 *      <li>BigDecimal</li>
 *  </ul>
 *  are registered by default.<br/><br/>
 *
 *  To read and write Enums, custom reader/writer with the specific type has to be registed:
 *  <pre>
 *      ...
 *      .registerReader(EnumType.class, new EnumAttributeReader<>(EnumType.class))
 *      .registerWriter(EnumType.class, new EnumAttributeWriter<>())
 *      ...
 *  </pre>
 */
public class DefaultFixedLengthStringAccessorConfigBuilder
        extends AccessorConfigBuilder<FixedLengthString, FixedLengthStringAttribute<?>> {

    public DefaultFixedLengthStringAccessorConfigBuilder() {
        registerBuildInReader();
        registerBuildInWriter();
    }

    private void registerBuildInReader() {
        registerReader(String.class, new StringAttributeReader());
        registerReader(Byte.class, new ByteAttributeReader());
        registerReader(Short.class, new ShortAttributeReader());
        registerReader(Integer.class, new IntegerAttributeReader());
        registerReader(Long.class, new LongAttributeReader());
        registerReader(Float.class, new FloatAttributeReader());
        registerReader(Double.class, new DoubleAttributeReader());
        registerReader(BigInteger.class, new BigIntegerAttributeReader());
        registerReader(BigDecimal.class, new BigDecimalAttributeReader());
    }

    private void registerBuildInWriter() {
        registerWriter(String.class, new StringAttributeWriter());
        registerWriter(Byte.class, new ByteAttributeWriter());
        registerWriter(Short.class, new ShortAttributeWriter());
        registerWriter(Integer.class, new IntegerAttributeWriter());
        registerWriter(Long.class, new LongAttributeWriter());
        registerWriter(Float.class, new FloatAttributeWriter());
        registerWriter(Double.class, new DoubleAttributeWriter());
        registerWriter(BigInteger.class, new BigIntegerAttributeWriter());
        registerWriter(BigDecimal.class, new BigDecimalAttributeWriter());
    }
}
