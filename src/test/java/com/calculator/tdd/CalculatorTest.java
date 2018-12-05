package com.calculator.tdd;

import com.calculator.tdd.exceptions.CalculatorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void shouldRetrun0WhenEmptyNumber(){
        addShouldBe(0, "");
    }

    @Test
    void shouldReturnNumberWhenNumber(){
        addShouldBe(1, "1");
    }

    @Test
    void shouldReturnSumWhenTwoNumberWithDelimiterComma() {
        addShouldBe(3, "1,2");
    }

    @Test
    void shouldReturnSumWhenMultipleNumberWithDelimiterComma() {
        addShouldBe(6, "1,2,3");
    }

    @Test
    void shouldAcceptNewLineAsValidDelimiter() {
        addShouldBe(6, "1\n2,3");
    }

    @Test
    void shouldSupportDifferentDelimiter() {
        addShouldBe(6, "//[;]\n1;2;3");
    }

    @Test
    void shouldThrowExceptionWhenNumberIsNegative() {
        CalculatorException calculatorException = assertThrows(CalculatorException.class, () -> calculator.add("-1,3,5,-2"));
        assertEquals("negatives not allowed [-1, -2]", calculatorException.getMessage());
    }

    @Test
    void shouldExcludeGreateThanOrEqualTo1000() {
        addShouldBe(6, "//[;]\n1;1001;2;3");
    }

    @Test
    void shouldAcceptAnyLengthOfDelimiter() {
        addShouldBe(3, "//[***]\n1***2");
    }

    @Test
    void shouldAcceptMultipleDelimiters() {
        addShouldBe(6, "//[*][;]\n1*2;3");
    }

    @Test
    void shouldAcceptMultipleDelimitersWithAnyLength() {
        addShouldBe(10, "//[;;;][***][???]\n1***2;;;3???4");
    }

    private void addShouldBe(int expected, String number) {
        try {
            assertEquals(expected, calculator.add(number));
        } catch (CalculatorException e) {
            //nothing
        }
    }

}
