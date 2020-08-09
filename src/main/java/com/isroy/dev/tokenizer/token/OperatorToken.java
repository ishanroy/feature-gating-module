package com.isroy.dev.tokenizer.token;

import com.isroy.dev.tokenizer.token.types.Operator;
import com.isroy.dev.tokenizer.token.types.TokenType;

public class OperatorToken extends Token {
    Operator operator;

    public OperatorToken(TokenType tokenType, String value) {
        super(tokenType, value);
    }

    public OperatorToken(TokenType tokenType, String value, Operator operator) {
        super(tokenType, value);
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }
}
