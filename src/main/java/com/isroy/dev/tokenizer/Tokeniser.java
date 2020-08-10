package com.isroy.dev.tokenizer;

import com.isroy.dev.exception.InvalidExpressionException;
import com.isroy.dev.exception.InvalidTokenException;
import com.isroy.dev.tokenizer.token.Token;

import java.util.List;

public interface Tokeniser {
    public List<Token> convert(String expression) throws InvalidExpressionException, InvalidTokenException;
}
