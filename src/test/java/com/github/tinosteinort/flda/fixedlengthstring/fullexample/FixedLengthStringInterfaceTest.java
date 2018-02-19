package com.github.tinosteinort.flda.fixedlengthstring.fullexample;

import com.github.tinosteinort.flda.accessor.reader.ReadAccessor;
import com.github.tinosteinort.flda.accessor.writer.WriteAccessor;
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

import java.io.UnsupportedEncodingException;

public class FixedLengthStringInterfaceTest {

    private final LengthValidator validator = new LengthValidator(PersonDescriptor.LENGTH);

    private final FixedLengthStringAccessorConfig config = new FixedLengthStringAccessorConfigBuilder()
            .withDefaultReaders()
            .withDefaultWriters()
            .withRecordFactory(new FixedLengthStringFactory(PersonDescriptor.LENGTH, ' '))
            .withReadValidator(validator)
            .withWriteValidator(validator)
            .build();

    @Test public void testImport() throws UnsupportedEncodingException {

        final String dataFromFile = "Dagobert  Duck      100";

        final Person person = doImport(dataFromFile);

        Assert.assertEquals("Dagobert", person.getFirstname());
        Assert.assertEquals("Duck", person.getLastname());
        Assert.assertEquals(100, person.getAge());
    }

    public Person doImport(final String data) {

        final Person person = new Person();

        final ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> readAccessor = new ReadAccessor<>(config, new FixedLengthString(data));

        person.setFirstname(readAccessor.read(PersonDescriptor.FIRST_NAME));
        person.setLastname(readAccessor.read(PersonDescriptor.LAST_NAME));
        person.setAge(readAccessor.read(PersonDescriptor.AGE));

        return person;
    }

    @Test public void testExport() throws UnsupportedEncodingException {

        final Person person = new Person();
        person.setFirstname("Dagobert");
        person.setLastname("Duck");
        person.setAge(100);

        Assert.assertEquals(
                new FixedLengthString("Dagobert  Duck      100"),
                doExport(person));
    }

    public FixedLengthString doExport(final Person person) {

        final FixedLengthString row = config.createNewRecord();

        final WriteAccessor<FixedLengthString, FixedLengthStringAttribute<?>> writer = new WriteAccessor<>(config, row);

        writer.write(PersonDescriptor.FIRST_NAME, person.getFirstname());
        writer.write(PersonDescriptor.LAST_NAME, person.getLastname());
        writer.write(PersonDescriptor.AGE, person.getAge());

        return row;
    }

    @Test public void testImportCustomAttribute() {

        final FixedLengthStringAccessorConfig localConfig = new FixedLengthStringAccessorConfigBuilder(config)
                // override default behaviour for special attribute:
                .registerReader(PersonDescriptor.AGE, new AgeDecrementIntegerWriter(1))
                .build();

        final String dataFromFile = "Tick      Duck        8";

        final Person person = new Person();

       final ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> readAccessor = new ReadAccessor<>(localConfig, new FixedLengthString(dataFromFile));

        person.setFirstname(readAccessor.read(PersonDescriptor.FIRST_NAME));
        person.setLastname(readAccessor.read(PersonDescriptor.LAST_NAME));
        person.setAge(readAccessor.read(PersonDescriptor.AGE));

        Assert.assertEquals("Tick", person.getFirstname());
        Assert.assertEquals("Duck", person.getLastname());
        Assert.assertEquals(7, person.getAge());
    }

    @Test public void testExportCustomAttribute() {

        final FixedLengthStringAccessorConfig localConfig = new FixedLengthStringAccessorConfigBuilder(config)
                // override default behaviour for special attribute
                .registerWriter(PersonDescriptor.AGE, new AgeIncrementIntegerWriter(2))
                .build();

        final Person person = new Person();
        person.setFirstname("Tick");
        person.setLastname("Duck");
        person.setAge(7); // Age should be right aligned, see definition in PersonDescriptor

        final FixedLengthString row = localConfig.createNewRecord();

        final WriteAccessor<FixedLengthString, FixedLengthStringAttribute<?>> writer = new WriteAccessor<>(localConfig, row);

        writer.write(PersonDescriptor.FIRST_NAME, person.getFirstname());
        writer.write(PersonDescriptor.LAST_NAME, person.getLastname());
        writer.write(PersonDescriptor.AGE, person.getAge());

        Assert.assertEquals(
                new FixedLengthString("Tick      Duck        9"),
                row);
    }

    @Test public void genericParameterTest() {

        final FixedLengthStringAccessorConfig localConfig = new FixedLengthStringAccessorConfigBuilder(config)
                .registerReader(String.class, new StringAttributeReader())
                .registerReader(Integer.class, new IntegerAttributeReader())
                .registerWriter(String.class, new StringAttributeWriter())
                .registerWriter(Integer.class, new IntegerAttributeWriter())
                .build();

        final String data = "Tick      Duck        7";

        final ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> reader = new ReadAccessor<>(localConfig, new FixedLengthString(data));
        final WriteAccessor<FixedLengthString, FixedLengthStringAttribute<?>> writer = new WriteAccessor<>(localConfig, new FixedLengthString(data));
        // This two Lines of Code does not compile properly if everything works fine
//        final String age = reader.read(PersonDescriptor.AGE);
//        writer.write(PersonDescriptor.AGE, "123");
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