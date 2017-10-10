package com.github.anastasiazhukova.calculator;

import android.content.Intent;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class CalculatorActivityAndroidTest {

    private ActivityTestRule<CalculatorActivity> calculatorActivity = new ActivityTestRule<>(CalculatorActivity.class);
    private ViewInteraction buttonOne;
    private ViewInteraction valueTextView;

    @Before
    public void runActivity() {
        calculatorActivity.launchActivity(new Intent());
        valueTextView = onView(withId(R.id.textView_value));
    }

    @Test
    public void testValueTextView() {
        final String targetTextView = "0";
        valueTextView.check(matches(isDisplayed()));
        valueTextView.check(new ViewAssertion() {

            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                if (!((TextView) view).getText().toString().equals(targetTextView)) {
                    throw new IllegalStateException("Text view does'n match");
                }
            }
        });
    }

    @Test
    public void testButtonOneOnClick() {
        final String targetTextView = "11";
        buttonOne = onView(withId(R.id.button_1));
        buttonOne.perform(click());
        buttonOne.perform(click());
        ViewAssertion textViewAssertion = new ViewAssertion() {

            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                if (!((TextView) view).getText().toString().equals(targetTextView)) {
                    throw new IllegalStateException("Text view does'n match");
                }
            }
        };
        valueTextView.check(textViewAssertion);
    }

    @Test
    public void testRetButtonOnClick() {
        ViewInteraction retButton = onView(withId(R.id.button_return));
        retButton.check(matches(isDisplayed()));
        retButton.check(matches(isEnabled()));

        buttonOne = onView(withId(R.id.button_1));
        buttonOne.perform(click());
        buttonOne.perform(click());
        buttonOne.perform(click());

        retButton.perform(click());

        valueTextView.check(matches(withText("11")));

    }
}