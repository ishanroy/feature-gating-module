package com.isroy.dev.tokenizer.classifier;

import com.isroy.dev.tokenizer.token.Token;

public interface TokenClassifier {
    public Token getTokenType(String token);
}
