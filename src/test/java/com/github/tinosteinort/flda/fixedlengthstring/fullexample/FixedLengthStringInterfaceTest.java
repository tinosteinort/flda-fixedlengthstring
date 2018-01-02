package com.github.tinosteinort.flda.fixedlengthstring.fullexample;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.AccessorConfigBuilder;
import com.github.tinosteinort.flda.accessor.reader.ReadAccessor;
import com.github.tinosteinort.flda.accessor.writer.WriteAccessor;
import com.github.tinosteinort.flda.fixedlengthstring.FixedLengthString;
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

    private final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config = new AccessorConfigBuilder<FixedLengthString, FixedLengthStringAttribute<?>>()
            .registerReader(String.class, new StringAttributeReader())
            .registerReader(Integer.class, new IntegerAttributeReader())
            .registerWriter(String.class, new StringAttributeWriter())
            .registerWriter(Integer.class, new IntegerAttributeWriter())
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

        final PersonFixedLengthStringReader reader = new PersonFixedLengthStringReader(config, new FixedLengthString(data));

        person.setFirstname(reader.firstname());
        person.setLastname(reader.lastname());
        person.setAge(reader.age());

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

        final PersonFixedLengthStringWriter writer = new PersonFixedLengthStringWriter(config, row);

        writer.firstname(person.getFirstname());
        writer.lastname(person.getLastname());
        writer.age(person.getAge());

        return row;
    }

    @Test public void testImportCustomAttribute() {

        final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> localConfig = new AccessorConfigBuilder<>(config)
                // not needed, because trim() removes spaces from left and right:
                // .registerReader(PersonDescriptor.AGE, new RightAlignedIntegerAttributeReader())
                .build();

        // Age is right aligned, custom AttributeReader required for reading
        final String dataFromFile = "Tick      Duck        7";

        final Person person = new Person();

        final PersonFixedLengthStringReader reader = new PersonFixedLengthStringReader(localConfig, new FixedLengthString(dataFromFile));

        person.setFirstname(reader.firstname());
        person.setLastname(reader.lastname());
        person.setAge(reader.age());

        Assert.assertEquals("Tick", person.getFirstname());
        Assert.assertEquals("Duck", person.getLastname());
        Assert.assertEquals(7, person.getAge());
    }

    @Test public void testExportCustomAttribute() {

        final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> localConfig = new AccessorConfigBuilder<>(config)
                .registerWriter(PersonDescriptor.AGE, new AgeIncrementIntegerWriter(2)) // override default behaviour for special Attribute
                .build();

        final Person person = new Person();
        person.setFirstname("Tick");
        person.setLastname("Duck");
        person.setAge(7); // Age should be right aligned, custom AttributeWriter required for writing

        final FixedLengthString row = localConfig.createNewRecord();

        final PersonFixedLengthStringWriter writer = new PersonFixedLengthStringWriter(localConfig, row);

        writer.firstname(person.getFirstname());
        writer.lastname(person.getLastname());
        writer.age(person.getAge());

        Assert.assertEquals(
                new FixedLengthString("Tick      Duck        9"),
                row);
    }

    @Test public void genericParameterTest() {

        final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> localConfig = new AccessorConfigBuilder<>(config)
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