package com.github.tinosteinort.flda.fixedlengthstring.fullexample;

import com.github.tinosteinort.flda.accessor.ReadAccessor;
import com.github.tinosteinort.flda.accessor.WriteAccessor;
import com.github.tinosteinort.flda.fixedlengthstring.Alignment;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthStringAccessorConfig;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthStringAccessorConfigBuilder;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthStringAttribute;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthStringFactory;
import com.github.tinosteinort.flda.fixedlengthstring.LengthValidator;
import com.github.tinosteinort.flda.fixedlengthstring.reader.IntegerAttributeReader;
import com.github.tinosteinort.flda.fixedlengthstring.reader.StringAttributeReader;
import com.github.tinosteinort.flda.fixedlengthstring.writer.IntegerAttributeWriter;
import com.github.tinosteinort.flda.fixedlengthstring.writer.StringAttributeWriter;
import org.junit.Assert;
import org.junit.Test;

public class FixedLengthStringInterfaceTest {

    private static final int RECORD_LENGTH = 24;
    private static final FixedLengthStringAttribute<String> FIRST_NAME = new FixedLengthStringAttribute<>(String.class, 0, 10, '.');
    private static final FixedLengthStringAttribute<String> LAST_NAME = new FixedLengthStringAttribute<>(String.class, 10, 10, '.');
    private static final FixedLengthStringAttribute<Integer> AGE = new FixedLengthStringAttribute<>(Integer.class, 20, 4, Alignment.RIGHT, '#');

    private final FixedLengthStringAccessorConfig config = new FixedLengthStringAccessorConfigBuilder()
            .withDefaultReaders()
            .withDefaultWriters()
            .withRecordFactory(new FixedLengthStringFactory(RECORD_LENGTH, '_'))
            .withReadValidator(new LengthValidator(RECORD_LENGTH))
            .withWriteValidator(new LengthValidator(RECORD_LENGTH))
            .build();

    @Test public void testReadExample() {

        final FixedLengthString record = new FixedLengthString("Dagobert..Duck......#100");

        final ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> readAccessor =
                config.newReadAccessor(record);

        final Person person = new Person();
        person.setFirstname(readAccessor.read(FIRST_NAME));
        person.setLastname(readAccessor.read(LAST_NAME));
        person.setAge(readAccessor.read(AGE));

        Assert.assertEquals("Dagobert", person.getFirstname());
        Assert.assertEquals("Duck", person.getLastname());
        Assert.assertEquals(100, person.getAge());
    }

    @Test public void testWriteExample() {

        final Person person = new Person();
        person.setFirstname("Dagobert");
        person.setLastname("Duck");
        person.setAge(100);

        final FixedLengthString generatedRecord = config.createNewRecord();

        final WriteAccessor<FixedLengthString, FixedLengthStringAttribute<?>> writer = config.newWriteAccessor(generatedRecord);

        writer.write(FIRST_NAME, person.getFirstname());
        writer.write(LAST_NAME, person.getLastname());
        writer.write(AGE, person.getAge());

        Assert.assertEquals(
                new FixedLengthString("Dagobert..Duck......#100"),
                generatedRecord);
    }

    @Test public void testImportCustomAttribute() {

        final FixedLengthStringAccessorConfig localConfig = new FixedLengthStringAccessorConfigBuilder(config)
                // override default behaviour for special attribute:
                .registerReader(AGE, new AgeDecrementIntegerWriter(1))
                .build();

        final String dataFromFile = "Tick......Duck......###8";

        final Person person = new Person();

       final ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> readAccessor =
               localConfig.newReadAccessor(new FixedLengthString(dataFromFile));

        person.setFirstname(readAccessor.read(FIRST_NAME));
        person.setLastname(readAccessor.read(LAST_NAME));
        person.setAge(readAccessor.read(AGE));

        Assert.assertEquals("Tick", person.getFirstname());
        Assert.assertEquals("Duck", person.getLastname());
        Assert.assertEquals(7, person.getAge());
    }

    @Test public void testExportCustomAttribute() {

        final FixedLengthStringAccessorConfig localConfig = new FixedLengthStringAccessorConfigBuilder(config)
                // override default behaviour for special attribute
                .registerWriter(AGE, new AgeIncrementIntegerWriter(2))
                .build();

        final Person person = new Person();
        person.setFirstname("Tick");
        person.setLastname("Duck");
        person.setAge(7); // Age should be right aligned, see definition in PersonDescriptor

        final FixedLengthString row = localConfig.createNewRecord();

        final WriteAccessor<FixedLengthString, FixedLengthStringAttribute<?>> writer =
                localConfig.newWriteAccessor(row);

        writer.write(FIRST_NAME, person.getFirstname());
        writer.write(LAST_NAME, person.getLastname());
        writer.write(AGE, person.getAge());

        Assert.assertEquals(
                new FixedLengthString("Tick......Duck......###9"),
                row);
    }

    @Test public void genericParameterTest() {

        final FixedLengthStringAccessorConfig localConfig = new FixedLengthStringAccessorConfigBuilder(config)
                .registerReader(String.class, new StringAttributeReader())
                .registerReader(Integer.class, new IntegerAttributeReader())
                .registerWriter(String.class, new StringAttributeWriter())
                .registerWriter(Integer.class, new IntegerAttributeWriter())
                .build();

        final String data = "Tick......Duck......###7";

        final ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> reader =
                localConfig.newReadAccessor(new FixedLengthString(data));
        final WriteAccessor<FixedLengthString, FixedLengthStringAttribute<?>> writer =
                localConfig.newWriteAccessor(new FixedLengthString(data));
        // This two Lines of Code does not compile properly if everything works fine
//        final String age = reader.read(AGE);
//        writer.write(AGE, "123");
    }
}

class AgeIncrementIntegerWriter extends IntegerAttributeWriter {

    private final int yearsToIncrement;

    public AgeIncrementIntegerWriter(final int yearsToIncrement) {
        this.yearsToIncrement = yearsToIncrement;
    }

    @Override protected String nullSafeConvertToString(final Integer value) {
        final Integer newValue = (value == null ? 0 : value) + yearsToIncrement;
        return String.valueOf(newValue);
    }
}

class AgeDecrementIntegerWriter extends IntegerAttributeReader {

    private final int yearsToDecrement;

    public AgeDecrementIntegerWriter(final int yearsToDecrement) {
        this.yearsToDecrement = yearsToDecrement;
    }

    @Override public Integer read(final FixedLengthString data, final FixedLengthStringAttribute<Integer> attribute) {
        final Integer value = super.read(data, attribute);
        return (value == null ? 0 : (value - yearsToDecrement));
    }
}

class Person {

    private String firstname;
    private String lastname;
    private int age;

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
