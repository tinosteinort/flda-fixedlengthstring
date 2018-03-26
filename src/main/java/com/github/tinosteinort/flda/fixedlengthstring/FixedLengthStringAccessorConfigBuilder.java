package com.github.tinosteinort.flda.fixedlengthstring;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.AccessorConfigBuilder;
import com.github.tinosteinort.flda.accessor.Attribute;
import com.github.tinosteinort.flda.accessor.AttributeReader;
import com.github.tinosteinort.flda.accessor.AttributeWriter;
import com.github.tinosteinort.flda.accessor.RecordFactory;
import com.github.tinosteinort.flda.accessor.RecordValidator;
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
 * AccessorConfigBuilder for {@link FixedLengthStringAttribute}.
 *
 *  To read and write Enums, custom reader/writer with the specific type has to be registered:
 *  <pre>
 *      ...
 *      .registerReader(EnumType.class, new EnumAttributeReader&lt;&gt;(EnumType.class))
 *      .registerWriter(EnumType.class, new EnumAttributeWriter&lt;&gt;())
 *      ...
 *  </pre>
 *
 *  There is no build in reader/writer for attributes of type {@link Boolean}.
 */
public class FixedLengthStringAccessorConfigBuilder
        extends AccessorConfigBuilder<FixedLengthString, FixedLengthStringAttribute<?>> {

    public FixedLengthStringAccessorConfigBuilder() {

    }

    public FixedLengthStringAccessorConfigBuilder(
            final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> baseConfig) {
        super(baseConfig);
    }

    /**
     * Registers the build in readers for types:
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
     *
     * @return the Builder for method chaining
     */
    public FixedLengthStringAccessorConfigBuilder withDefaultReaders() {
        registerReader(String.class, new StringAttributeReader());
        registerReader(Byte.class, new ByteAttributeReader());
        registerReader(Short.class, new ShortAttributeReader());
        registerReader(Integer.class, new IntegerAttributeReader());
        registerReader(Long.class, new LongAttributeReader());
        registerReader(Float.class, new FloatAttributeReader());
        registerReader(Double.class, new DoubleAttributeReader());
        registerReader(BigInteger.class, new BigIntegerAttributeReader());
        registerReader(BigDecimal.class, new BigDecimalAttributeReader());

        return this;
    }

    /**
     * Registers the build in writers for types:
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
     *
     * @return the Builder for method chaining
     */
    public FixedLengthStringAccessorConfigBuilder withDefaultWriters() {
        registerWriter(String.class, new StringAttributeWriter());
        registerWriter(Byte.class, new ByteAttributeWriter());
        registerWriter(Short.class, new ShortAttributeWriter());
        registerWriter(Integer.class, new IntegerAttributeWriter());
        registerWriter(Long.class, new LongAttributeWriter());
        registerWriter(Float.class, new FloatAttributeWriter());
        registerWriter(Double.class, new DoubleAttributeWriter());
        registerWriter(BigInteger.class, new BigIntegerAttributeWriter());
        registerWriter(BigDecimal.class, new BigDecimalAttributeWriter());

        return this;
    }

    @Override public <T> FixedLengthStringAccessorConfigBuilder registerReader(
            final Class<T> type, final AttributeReader<FixedLengthString, T, ? extends Attribute<T>> reader) {
        return (FixedLengthStringAccessorConfigBuilder) super.registerReader(type, reader);
    }

    @Override public <T> FixedLengthStringAccessorConfigBuilder registerWriter(
             final Class<T> type, final AttributeWriter<FixedLengthString, T, ? extends Attribute<T>> writer) {
        return (FixedLengthStringAccessorConfigBuilder) super.registerWriter(type, writer);
    }

    @Override public <T> FixedLengthStringAccessorConfigBuilder registerReader(
            final FixedLengthStringAttribute<?> attribute,
            final AttributeReader<FixedLengthString, T, ? extends Attribute<T>> reader) {
        return (FixedLengthStringAccessorConfigBuilder) super.registerReader(attribute, reader);
    }

    @Override public <T>FixedLengthStringAccessorConfigBuilder registerWriter(
            final FixedLengthStringAttribute<?> attribute,
            final AttributeWriter<FixedLengthString, T, ? extends Attribute<T>> writer) {
        return (FixedLengthStringAccessorConfigBuilder) super.registerWriter(attribute, writer);
    }

    @Override public FixedLengthStringAccessorConfigBuilder withRecordFactory(
            final RecordFactory<FixedLengthString> recordFactory) {
        return (FixedLengthStringAccessorConfigBuilder) super.withRecordFactory(recordFactory);
    }

    @Override public FixedLengthStringAccessorConfigBuilder withReadValidator(
            final RecordValidator<FixedLengthString> validator) {
        return (FixedLengthStringAccessorConfigBuilder) super.withReadValidator(validator);
    }

    @Override public FixedLengthStringAccessorConfigBuilder withWriteValidator(
            final RecordValidator<FixedLengthString> validator) {
        return (FixedLengthStringAccessorConfigBuilder) super.withWriteValidator(validator);
    }

    @Override public FixedLengthStringAccessorConfig build() {
        return new FixedLengthStringAccessorConfig(readersByType, writersByType, readersByAttribute, writersByAttribute,
                recordFactory, readValidator, writeValidator) {
        };
    }
}
