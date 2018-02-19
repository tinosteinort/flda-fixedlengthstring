package com.github.tinosteinort.flda.fixedlengthstring;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.reader.ReadAccessor;
import com.github.tinosteinort.flda.accessor.writer.WriteAccessor;

/**
 * Accessor configuration class of FLDA for records of type {@link FixedLengthString}.
 */
public interface FixedLengthStringAccessorConfig extends AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> {

    /**
     * Creates a new {@link ReadAccessor} for the given record.
     *
     * @param record to read from
     * @return a new {@code ReadAccessor} for the record.
     */
    ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> newReadAccessor(final FixedLengthString record);

    /**
     * Creates a new {@link WriteAccessor} for the given record.
     *
     * @param record to write to
     * @return a new {@code WriteAccessor} for the record.
     */
    WriteAccessor<FixedLengthString, FixedLengthStringAttribute<?>> newWriteAccessor(final FixedLengthString record);
}
