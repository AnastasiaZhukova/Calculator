package com.github.anastasiazhukova.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class CalculatorTest {

    private Value mValue1;
    private Value mValue2;
    private Value mResultValue;
    private ICalculator mCalculator;

    @Before
    public void setUp() {
        mCalculator = new Calculator();
        mValue1 = spy(Value.class);
        mValue2 = mock(Value.class);
        when(mValue1.ConvertToNumber()).thenReturn(10.0);
        when(mValue2.ConvertToNumber()).thenReturn(5.0);
    }

    @Test
    public void add() throws Exception {
        mResultValue = mCalculator.add(mValue1, mValue2);
        assertEquals(mResultValue.ConvertToString(), "15.0");
    }

    @Test
    public void subtract() throws Exception {
        mResultValue = mCalculator.subtract(mValue1, mValue2);
        assertEquals(mResultValue.ConvertToString(), "5.0");
    }

    @Test
    public void multiply() throws Exception {
        mResultValue = mCalculator.multiply(mValue1, mValue2);
        assertEquals(mResultValue.ConvertToString(), "50.0");
    }

    @Test
    public void divide() throws Exception {
        mResultValue = mCalculator.divide(mValue1, mValue2);
        assertEquals(mResultValue.ConvertToString(), "2.0");
    }

    @Test
    public void evaluate() throws Exception {
        Operations sum = Operations.ADDITION;
        mResultValue = mCalculator.evaluate(mValue1, sum, mValue2);
        assertEquals(mResultValue.ConvertToString(), "15.0");
    }

    @Test
    public void testSpy() {
        final double delta = 0;
        final double number=10.0;
        mValue1.setValue(15);
        assertEquals(mValue1.isZeroValue(), false);
        assertEquals(mValue1.ConvertToNumber(), number, delta);
    }

    @Test
    public void testMock() {
        final double delta = 0;
        final double number=5.0;
        mValue2.setValue(0);
        assertEquals("Test fails because of mock",mValue2.isZeroValue(), true);
        assertEquals(mValue2.ConvertToNumber(), number, delta);
    }

}