package com.github.anastasiazhukova.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    public static final String ERROR_MESSAGE = "Error!";
    ICalculator mCalculator = new Calculator();
    private Value value1 = new Value();
    private Value value2 = new Value();
    private Value currentValue;
    private Operations currentOperation;
    private View currentChosenButton;

    private TextView mValueTextView;
    private View mAcButton;
    private View mPlusMinusButton;
    private View mRetButton;
    private View mDivideButton;
    private View mMultiplyButton;
    private View mMinusButton;
    private View mPlusButton;
    private View mEvaluateButton;
    private View mDotButton;
    private View mZeroButton;
    private View mOneButton;
    private View mTwoButton;
    private View mThreeButton;
    private View mFourButton;
    private View mFiveButton;
    private View mSixButton;
    private View mSevenButton;
    private View mEightButton;
    private View mNineButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        findViews();
        setOnClickListeners();
        setValuesToDefault();
        showValue(currentValue);
        Log.d("Finished", "onCreate finished it's work");
    }

    private void findViews() {
        mValueTextView = (TextView) findViewById(R.id.textView_value);
        mAcButton = findViewById(R.id.button_AC);
        mPlusMinusButton = findViewById(R.id.button_plus_minus);
        mRetButton = findViewById(R.id.button_return);
        mDivideButton = findViewById(R.id.button_divide);
        mMultiplyButton = findViewById(R.id.button_multiply);
        mMinusButton = findViewById(R.id.button_minus);
        mPlusButton = findViewById(R.id.button_plus);
        mEvaluateButton = findViewById(R.id.button_evaluate);
        mDotButton = findViewById(R.id.button_dot);
        mZeroButton = findViewById(R.id.button_0);
        mOneButton = findViewById(R.id.button_1);
        mTwoButton = findViewById(R.id.button_2);
        mThreeButton = findViewById(R.id.button_3);
        mFourButton = findViewById(R.id.button_4);
        mFiveButton = findViewById(R.id.button_5);
        mSixButton = findViewById(R.id.button_6);
        mSevenButton = findViewById(R.id.button_7);
        mEightButton = findViewById(R.id.button_8);
        mNineButton = findViewById(R.id.button_9);
    }

    private void setValuesToDefault() {
        value1.setValue(0);
        value2.setValue(0);
        currentValue = value1;
    }

    private void setAllButtonsDefaultColor(final int resId) {
        mDivideButton.setBackgroundResource(resId);
        mMultiplyButton.setBackgroundResource(resId);
        mPlusButton.setBackgroundResource(resId);
        mMinusButton.setBackgroundResource(resId);
    }

    private void setAllButtonsState(final boolean state) {
        mAcButton.setEnabled(state);
        mPlusMinusButton.setEnabled(state);
        mRetButton.setEnabled(state);
        mDivideButton.setEnabled(state);
        mMultiplyButton.setEnabled(state);
        mMinusButton.setEnabled(state);
        mPlusButton.setEnabled(state);
        mEvaluateButton.setEnabled(state);
        mDotButton.setEnabled(state);
        mZeroButton.setEnabled(state);
        mOneButton.setEnabled(state);
        mTwoButton.setEnabled(state);
        mThreeButton.setEnabled(state);
        mFourButton.setEnabled(state);
        mFiveButton.setEnabled(state);
        mSixButton.setEnabled(state);
        mSevenButton.setEnabled(state);
        mEightButton.setEnabled(state);
        mNineButton.setEnabled(state);
    }

    private void resetAll() {
        setValuesToDefault();
        setButtonsToDefault();
        currentChosenButton = null;
    }

    private void resetForNextOperation() {
        mDotButton.setEnabled(true);
        value2.setValue(0);
        currentValue = value2;
    }

    private void setButtonsToDefault() {
        setAllButtonsDefaultColor(R.color.colorPrimary);
        setAllButtonsState(true);
    }

    private void showValue(final Value pValue) {
        mValueTextView.setText(pValue.ConvertToString());
    }

    private void chooseButton(final View pButton) {
        if (currentChosenButton != null) {
            currentChosenButton.setBackgroundResource(R.color.colorPrimary);
        }
        currentChosenButton = pButton;
        currentChosenButton.setBackgroundResource(R.color.colorAccent);
    }

    private void setErrorMode() {
        mValueTextView.setText(ERROR_MESSAGE);
        setAllButtonsState(false);
        mAcButton.setEnabled(true);
    }

    private void setOnClickListeners() {
        setAcButtonOnClick();
        setPlusMinusButtonOnClick();
        setRetButtonOnClick();
        setDotButtonOnClick();
        setEvaluateButtonOnClick();
        setDivideButtonOnClick();
        setMultiplyButtonOnClick();
        setMinusButtonOnClick();
        setPlusButtonOnClick();
        setZeroButton();
        setOneButtonOnClick();
        setTwoButtonOnClick();
        setThreeButtonOnCLick();
        setFourButtonOnCLick();
        setFiveButtonOnClick();
        setSixButtonOnClick();
        setSevenButtonOnClick();
        setEightButtonOnClick();
        setNineButtonOnCLick();
    }

    private void setAcButtonOnClick() {
        mAcButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                resetAll();
                showValue(currentValue);
            }
        });
    }

    private void setPlusMinusButtonOnClick() {
        mPlusMinusButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                currentValue.changeSign();
                showValue(currentValue);
            }
        });
    }

    private void setRetButtonOnClick() {
        mRetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                final String tempValue = currentValue.ConvertToString();
                if (tempValue.charAt(tempValue.length() - 1) == '.') {
                    mDotButton.setEnabled(true);
                }
                currentValue.RemoveLast();
                showValue(currentValue);
            }
        });
    }

    private void setDivideButtonOnClick() {
        mDivideButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                if (currentValue == value2 && currentOperation != null) {
                    if (!currentValue.isZeroValue()) {
                        value1 = mCalculator.evaluate(value1, currentOperation, value2);
                        showValue(value1);
                    } else {
                        setErrorMode();
                        return;
                    }
                }
                chooseButton(mDivideButton);
                currentOperation = Operations.DIVISION;
                resetForNextOperation();

            }
        });
    }

    private void setMultiplyButtonOnClick() {
        mMultiplyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                if (currentValue == value2 && !value2.isZeroValue() && currentOperation != null) {
                    value1 = mCalculator.evaluate(value1, currentOperation, value2);
                    showValue(value1);
                }
                resetForNextOperation();
                chooseButton(mMultiplyButton);
                currentOperation = Operations.MULTIPLICATION;
            }
        });
    }

    private void setMinusButtonOnClick() {
        mMinusButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                if (currentValue == value2 && !value2.isZeroValue() && currentOperation != null) {
                    value1 = mCalculator.evaluate(value1, currentOperation, value2);
                    showValue(value1);
                }
                resetForNextOperation();
                chooseButton(mMinusButton);
                currentOperation = Operations.SUBTRACTION;
            }
        });
    }

    private void setPlusButtonOnClick() {
        mPlusButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                if (currentValue == value2 && !value2.isZeroValue() && currentOperation != null) {
                    value1 = mCalculator.evaluate(value1, currentOperation, value2);
                    showValue(value1);
                }
                resetForNextOperation();
                //showValue(value1);
                chooseButton(mPlusButton);
                currentOperation = Operations.ADDITION;
                //currentValue = value2;
                //showValue(currentValue);
            }
        });
    }

    private void setEvaluateButtonOnClick() {
        mEvaluateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                Value tempValue;
                if (currentOperation == null) {
                    return;
                }
                try {
                    tempValue = mCalculator.evaluate(value1, currentOperation, value2);
                } catch (final ArithmeticException ae) {
                    setErrorMode();
                    return;
                }
                resetAll();
                value1 = tempValue;
                showValue(value1);
                resetForNextOperation();
                currentOperation = null;
            }
        });
    }

    private void setDotButtonOnClick() {
        mDotButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                currentValue.Append('.');
                showValue(currentValue);
                mDotButton.setEnabled(false);
            }
        });
    }

    private void setZeroButton() {
        mZeroButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                currentValue.Append('0');
                showValue(currentValue);
            }
        });

    }

    private void setOneButtonOnClick() {
        mOneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                currentValue.Append('1');
                showValue(currentValue);
            }
        });
    }

    private void setTwoButtonOnClick() {
        mTwoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                currentValue.Append('2');
                showValue(currentValue);
            }
        });

    }

    private void setThreeButtonOnCLick() {
        mThreeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                currentValue.Append('3');
                showValue(currentValue);
            }
        });

    }

    private void setFourButtonOnCLick() {
        mFourButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                currentValue.Append('4');
                showValue(currentValue);
            }
        });
    }

    private void setFiveButtonOnClick() {
        mFiveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                currentValue.Append('5');
                showValue(currentValue);
            }
        });
    }

    private void setSixButtonOnClick() {
        mSixButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                currentValue.Append('6');
                showValue(currentValue);
            }
        });
    }

    private void setSevenButtonOnClick() {
        mSevenButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                currentValue.Append('7');
                showValue(currentValue);
            }
        });
    }

    private void setEightButtonOnClick() {
        mEightButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                currentValue.Append('8');
                showValue(currentValue);
            }
        });
    }

    private void setNineButtonOnCLick() {
        mNineButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                currentValue.Append('9');
                showValue(currentValue);
            }
        });
    }

}
