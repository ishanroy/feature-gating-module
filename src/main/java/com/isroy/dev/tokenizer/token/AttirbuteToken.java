package com.isroy.dev.tokenizer.token;

import com.isroy.dev.tokenizer.token.types.Attribute;
import com.isroy.dev.tokenizer.token.types.TokenType;

public class AttirbuteToken extends Token {

    Attribute attribute;

    public AttirbuteToken(TokenType tokenType, String value, Attribute attribute) {
        super(tokenType, value);
        this.attribute = attribute;
    }

    public Attribute getAttributes() {
        return attribute;
    }


}
