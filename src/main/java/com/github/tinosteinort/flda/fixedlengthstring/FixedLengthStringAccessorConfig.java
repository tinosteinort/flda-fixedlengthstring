package com.github.tinosteinort.flda.fixedlengthstring;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.Attribute;
import com.github.tinosteinort.flda.accessor.AttributeReader;
import com.github.tinosteinort.flda.accessor.AttributeWriter;
import com.github.tinosteinort.flda.accessor.RecordFactory;
import com.github.tinosteinort.flda.accessor.RecordValidator;

import java.util.Map;

/**
 * Accessor configuration class of FLDA for records of type {@link FixedLengthString}.
 */
public abstract class FixedLengthStringAccessorConfig extends AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> {

    FixedLengthStringAccessorConfig(
            final Map<Class<?>, AttributeReader<FixedLengthString, ?, ? extends Attribute<?>>> readersByType,
            final Map<Class<?>, AttributeWriter<FixedLengthString, ?, ? extends Attribute<?>>> writersByType,
            final Map<FixedLengthStringAttribute<?>, AttributeReader<FixedLengthString, ?, ? extends Attribute<?>>> readersByAttribute,
            final Map<FixedLengthStringAttribute<?>, AttributeWriter<FixedLengthString, ?, ? extends Attribute<?>>> writersByAttribute,
            final RecordFactory<FixedLengthString> recordFactory,
            final RecordValidator<FixedLengthString> readValidator,
            final RecordValidator<FixedLengthString> writeValidator) {

        super(readersByType, writersByType, readersByAttribute, writersByAttribute, recordFactory, readValidator,
                writeValidator);
    }
}
