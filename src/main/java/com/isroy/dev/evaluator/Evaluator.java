package com.isroy.dev.evaluator;

import com.isroy.dev.exception.InvalidOperandException;
import com.isroy.dev.exception.InvalidTokenException;
import com.isroy.dev.tokenizer.token.Token;

import java.util.List;

public interface Evaluator<T> {
    T evaluate(List<Token> expression) throws InvalidTokenException, InvalidOperandException;
}
