package com.github.tinosteinort.flda.fixedlengthstring.writer;

import java.math.BigInteger;

public class BigIntegerAttributeWriter extends NumberAttributeWriter<BigInteger> {

    @Override protected String nullSafeConvertToString(final BigInteger value) {
        return String.valueOf(value);
    }
}
