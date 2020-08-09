package com.isroy.dev.evaluator.operators;

import com.isroy.dev.exception.InvalidOperandException;

import java.util.List;

public interface IOperatorEvaluator<E> {
    E evaluate(List<Object> operands) throws InvalidOperandException;
    boolean validate(List<Object> operands);

}
