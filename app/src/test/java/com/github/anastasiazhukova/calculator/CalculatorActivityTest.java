package com.github.anastasiazhukova.calculator;

import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class CalculatorActivityTest {

    private ActivityController<CalculatorActivity> mActivityController;
    private CalculatorActivity calculatorActivity;
    private View buttonOne;
    private View valueTextView;

    @Before
    public void setUp() {
        mActivityController = Robolectric.buildActivity(CalculatorActivity.class);
        calculatorActivity = mActivityController.get();
    }

    @Test
    public void testActivityRun() {
        mActivityController.create();
        mActivityController.start();
        mActivityController.resume();
        buttonOne = calculatorActivity.findViewById(R.id.button_1);
        valueTextView = calculatorActivity.findViewById(R.id.textView_value);
        assertEquals(buttonOne.isEnabled(), true);
        assertEquals(valueTextView.isEnabled(), true);
    }

    @Test
    public void testDotButtonBehavior() {
        mActivityController.create().start().resume();
        View buttonDot = calculatorActivity.findViewById(R.id.button_dot);
        assertEquals(buttonDot.isEnabled(), true);
        buttonDot.performClick();
        assertEquals(buttonDot.isEnabled(), false);
    }

    @Test
    public void testSimpleAddition() {
        mActivityController.create().start().resume();
        buttonOne = calculatorActivity.findViewById(R.id.button_1);
        valueTextView = calculatorActivity.findViewById(R.id.textView_value);
        View buttonPlus = calculatorActivity.findViewById(R.id.button_plus);
        View buttonEvaluate = calculatorActivity.findViewById(R.id.button_evaluate);
        buttonOne.performClick();
        buttonPlus.performClick();
        buttonOne.performClick();
        buttonEvaluate.performClick();
        assertEquals(((TextView) valueTextView).getText().toString(), "2.0");
    }
}

