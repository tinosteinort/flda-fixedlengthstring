package com.github.tinosteinort.flda.fixedlengthstring.writer;

import java.math.BigDecimal;

public class BigDecimalAttributeWriter extends NumberAttributeWriter<BigDecimal> {

    @Override protected String nullSafeConvertToString(final BigDecimal value) {
        return String.valueOf(value);
    }
}
