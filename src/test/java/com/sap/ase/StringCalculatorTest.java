package com.sap.ase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    private StringCalculator calculator = new StringCalculator();

    @Test
    public void should_treat_empty_string_as_zero() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void should_add_single_number() {
        assertEquals(17, calculator.add("17"));
    }

    @Test
    public void should_add_comma_separated_numbers() {
        assertEquals(2356, calculator.add("2341,15"));
    }

    @Test
    public void should_treat_new_line_as_separator() {
        assertEquals(35, calculator.add("34\n1"));
    }

    @Test
    public void should_support_custom_separators() {
        assertEquals(7, calculator.add("//;\n5;2"));
    }

    @Test
    public void should_throw_exception_if_number_is_negative() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.add("34,-20\n3"));
        assertEquals("negatives not allowed", exception.getMessage());
    }
}