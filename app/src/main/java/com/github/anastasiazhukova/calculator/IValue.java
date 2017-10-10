package com.github.anastasiazhukova.calculator;

public interface IValue {

    void Append(final char ch);

    void RemoveLast();

    double ConvertToNumber();

    String ConvertToString();

    void changeSign();

}
