package com.github.anastasiazhukova.calculator;

public class Value implements IValue {

    private String mValue;
    private static final String defaultZeroValue = "0";

    Value() {
        mValue = defaultZeroValue;
    }

    Value(final double pDoubleValue) {
        if (pDoubleValue == 0) {
            mValue = defaultZeroValue;
        } else {
            mValue = String.valueOf(pDoubleValue);
        }
    }

    void setValue(final double pDoubleValue) {
        if (pDoubleValue == 0) {
            mValue = defaultZeroValue;
        } else {
            mValue = String.valueOf(pDoubleValue);
        }
    }

    @Override
    public void Append(char ch) {

        StringBuilder stringBuilder = new StringBuilder(mValue);
        if (ConvertToNumber() == 0) {
            if (ch != '.') {
                if (!isDoubleValue()) {
                    stringBuilder = new StringBuilder("");
                }
            }
        }
        stringBuilder.append(ch);
        mValue = stringBuilder.toString();
    }

    @Override
    public void RemoveLast() {
        if (ConvertToNumber() == 0) {
            //checks if there is a dot
            if (!isDoubleValue()) {
                return;
            }
        }
        StringBuilder stringBuilder = new StringBuilder(mValue);
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        mValue = stringBuilder.toString();
        if (isZeroValue()) {
            mValue = defaultZeroValue;
        } else {
            mValue = stringBuilder.toString();
        }
    }

    @Override
    public double ConvertToNumber() {
        if (isZeroValue()) {
            return 0;
        } else {
            return Double.valueOf(mValue);
        }
    }

    @Override
    public String ConvertToString() {
        return mValue;
    }

    @Override
    public void changeSign() {
        final char sign = mValue.charAt(0);
        StringBuilder stringBuilder = new StringBuilder(mValue);
        stringBuilder = stringBuilder.reverse();
        if (sign == '-') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        } else {
            stringBuilder.append('-');
        }
        mValue = (stringBuilder.reverse()).toString();

    }

    private boolean isDoubleValue() {
        return mValue.contains(".");
    }

    boolean isZeroValue() {
        return mValue.isEmpty() || mValue == "-" || Double.valueOf(mValue) == 0;
    }
}
