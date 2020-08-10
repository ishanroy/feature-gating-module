package com.isroy.dev.tokenizer.token;

import com.isroy.dev.tokenizer.token.types.TokenType;

public class Token {
    protected TokenType tokenType;
    protected String value;

    public Token(TokenType tokenType, String value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getValue() {
        return value;
    }
}
