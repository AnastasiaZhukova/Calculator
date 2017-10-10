package com.github.anastasiazhukova.calculator;

public interface ICalculator {

    Value add(Value value1, Value value2);

    Value subtract(Value value1, Value value2);

    Value multiply(Value value1, Value value2);

    Value divide(Value value1, Value value2) throws ArithmeticException;

    Value evaluate(Value value1, Operations pOperation, Value value2);
}
