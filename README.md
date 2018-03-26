FLDA - Fixed Length String
==========================

Using [FLDA](https://github.com/tinosteinort/flda-core) with Fixed Length Strings.

## Fixed-length attributes
Fixed-length data consists of attributes that each have a specific position and length. Hence, every n-th attribute
 begins at the same character-index. In this example, every name of an item begins at character-index 10 and every
 amount of items begins at character-index 16:  
```
Fruit    Cherry30
Fruit    Apple  5
VegetablePotato23
```

## FixedLengthString
A `FixedLengthString` is a mutable String. The value can be changed, but not the size of the String.
 To create a `FixedLengthString` there are different possibilities:
1. `FixedLengthString fls = new FixedLengthString("some value");` creates a new FixedLengthString with 10 chars and a value of "some value".
2. `FixedLengthString fls = new FixedLengthString(10, '_');` creates a new FixedLengthString with 10 chars and a value of "__________"

`flda-fixedlengthstring` reads and writes a FixedLengthString with the definition of the attributes. Too short values
 are filled up with a definable Character and values which are too long will be cut.

See [FixedLengthStringInterfaceTest](src/test/java/com/github/tinosteinort/flda/fixedlengthstring/fullexample/FixedLengthStringInterfaceTest.java)
 for a full working example with:
* Length Validator
* RecordFactory
* Build in reading / writing of types:
  * String
  * Enum
  * Byte
  * Short
  * Integer
  * Long
  * Float
  * Double
  * BigInteger
  * BigDecimal
* extending a config and overwrite behavior for an attribute

## Maven

To use `flda-stringlist`, include the following artifacts:

```xml
<dependency>
    <groupId>com.github.tinosteinort</groupId>
    <artifactId>flda-core</artifactId>
    <version>2.0.0</version>
</dependency>
<dependency>
    <groupId>com.github.tinosteinort</groupId>
    <artifactId>flda-fixedlengthstring</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Java 9 Module

The Automatic Module Name of this library is:
```
com.github.tinosteinort.flda.fixedlengthstring
```
