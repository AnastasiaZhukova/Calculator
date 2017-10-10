package com.github.anastasiazhukova.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValueTest {

    private Value mValue1;
    private Value mValue2;

    @Before
    public void setUp() throws Exception {

        mValue1=new Value();
        mValue2=new Value(0);
    }

    @Test
    public void append() throws Exception {
        mValue1.Append('1');
        mValue1.Append('2');
        String result1=mValue1.ConvertToString();
        assertEquals(result1,"12");
    }

    @Test
    public void removeLast() throws Exception {
        mValue1.RemoveLast();
        String result1=mValue1.ConvertToString();
        assertEquals(result1,"0");
    }

    @Test
    public void convertToNumber() throws Exception {
        mValue2.setValue(12.0);
        double dValue2=mValue2.ConvertToNumber();
        assertEquals(dValue2, 12.0, 0);

    }

    @Test
    public void isZeroValue() throws Exception {
        mValue1.setValue(-0.2);
        assertEquals("value=-0.2",mValue1.isZeroValue(),false);
        mValue1.RemoveLast();
        assertEquals("value=-0.",mValue1.isZeroValue(),true);
        mValue1.RemoveLast();
        assertEquals("value=-0",mValue1.isZeroValue(),true);
        mValue1.RemoveLast();
        assertEquals("value=-",mValue1.isZeroValue(),true);
        mValue1.RemoveLast();
        assertEquals("value=",mValue1.isZeroValue(),true);
    }

    @Test
    public void changeValueSign() throws Exception {
        mValue1.setValue(-0.2);
        mValue1.changeSign();
        assertEquals("value=0.2",mValue1.ConvertToString(),"0.2");
        mValue1.changeSign();
        assertEquals("value=-0.2",mValue1.ConvertToString(),"-0.2");
    }

}