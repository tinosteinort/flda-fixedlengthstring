package com.github.tinosteinort.flda.fixedlengthstring;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.RecordFactory;

/**
 * This Factory creates new instances of {@link FixedLengthString} with a specific length. Instances of
 *  this class are used by {@link AccessorConfig#createNewRecord()} if the factory is registered
 *  with {@link com.github.tinosteinort.flda.accessor.AccessorConfigBuilder#withRecordFactory(RecordFactory)}.
 */
public class FixedLengthStringFactory implements RecordFactory<FixedLengthString> {

    private final int length;
    private final char initialFiller;

    /**
     * @param length the length of the {@link FixedLengthString}.
     * @param initialFiller the {@link Character} with which the FixedLengthString is filled.
     */
    public FixedLengthStringFactory(final int length, final char initialFiller) {
        this.length = length;
        this.initialFiller = initialFiller;
    }

    /**
     * Creates a new instance.
     *
     * @return a new instance of a {@link FixedLengthString}
     */
    @Override public FixedLengthString createNewRecord() {
        return new FixedLengthString(length, initialFiller);
    }
}
