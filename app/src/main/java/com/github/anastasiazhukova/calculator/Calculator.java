package com.github.anastasiazhukova.calculator;

public class Calculator implements ICalculator {

    @Override
    public Value add(final Value value1, final Value value2) {
        return new Value(value1.ConvertToNumber() + value2.ConvertToNumber());
    }

    @Override
    public Value subtract(final Value value1, final Value value2) {
        return new Value(value1.ConvertToNumber() - value2.ConvertToNumber());
    }

    @Override
    public Value multiply(final Value value1, final Value value2) {
        return new Value(value1.ConvertToNumber() * value2.ConvertToNumber());
    }

    @Override
    public Value divide(final Value value1, final Value value2) throws ArithmeticException {
        final double doubleValue2 = value2.ConvertToNumber();
        if (doubleValue2 == 0) {
            throw new ArithmeticException("Division by zero!");
        }
        return new Value(value1.ConvertToNumber() / doubleValue2);
    }

    @Override
    public Value evaluate(final Value value1, final Operations pOperation, final Value value2) {
        switch (pOperation) {
            case ADDITION:
                return add(value1, value2);
            case SUBTRACTION:
                return subtract(value1, value2);
            case MULTIPLICATION:
                return multiply(value1, value2);
            case DIVISION:
                try {
                    return divide(value1, value2);
                } catch (final ArithmeticException ae) {
                    throw ae;
                }
            default:
                throw new ArithmeticException();
        }
    }

}
