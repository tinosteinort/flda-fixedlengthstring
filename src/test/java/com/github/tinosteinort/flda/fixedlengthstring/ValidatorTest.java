package com.github.tinosteinort.flda.fixedlengthstring;

import com.github.tinosteinort.flda.accessor.ReadAccessor;
import com.github.tinosteinort.flda.accessor.WriteAccessor;
import org.junit.Test;

public class ValidatorTest {

    private final LengthValidator readValidator = new LengthValidator(5);
    private final LengthValidator writeValidator = new LengthValidator(5);

    private final FixedLengthStringAccessorConfig config = new FixedLengthStringAccessorConfigBuilder()
                    .withReadValidator(readValidator)
                    .withWriteValidator(writeValidator)
                    .build();

    @Test(expected = IllegalArgumentException.class)
    public void validateRead() {

        final FixedLengthString data = new FixedLengthString(4, ' ');
        new ReadAccessor<>(config, data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateWrite() {

        final FixedLengthString data = new FixedLengthString(6, ' ');
        new WriteAccessor<>(config, data);
    }

    @Test public void nullValidators() {
        final FixedLengthStringAccessorConfig config = new FixedLengthStringAccessorConfigBuilder()
                        .withReadValidator(null)
                        .withWriteValidator(null)
                        .build();

        final FixedLengthString data = new FixedLengthString(5, ' ');

        new ReadAccessor<>(config, data);
        new WriteAccessor<>(config, data);
    }
}
