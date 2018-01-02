package com.github.tinosteinort.flda.fixedlengthstring.reader;

import java.math.BigInteger;

public class BigIntegerAttributeReader extends NumberAttributeReader<BigInteger> {

    @Override protected BigInteger nullSafeConvertToNumber(final String value) {
        return new BigInteger(value);
    }
}
