package com.github.tinosteinort.flda.fixedlengthstring.reader;

import java.math.BigDecimal;

public class BigDecimalAttributeReader extends NumberAttributeReader<BigDecimal> {

    @Override protected BigDecimal nullSafeConvertToNumber(final String value) {
        return new BigDecimal(value);
    }
}
