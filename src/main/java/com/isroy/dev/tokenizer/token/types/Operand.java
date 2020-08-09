package com.isroy.dev.tokenizer.token.types;

import java.util.regex.Pattern;

public enum Operand {
    NUMBER(Pattern.compile("^(-?\\d+)$")),
    DECIMAL(Pattern.compile("^(-?\\d+(\\.\\d+)?)$")),
    NUMBERLIST(Pattern.compile("^(-?\\d+)(,-?\\d+)*$")),
    DECIMALLIST(Pattern.compile("^(-?\\d+(\\.\\d+)?)(,-?\\d+(\\.\\d+)?)*$")),
    STRING(Pattern.compile("\".*\"")),
    BOOLEAN(Pattern.compile("true|false", Pattern.CASE_INSENSITIVE));

    Pattern pattern;
    Operand(Pattern pattern){
        this.pattern = pattern;
    }

    public boolean isValid(String token){
        return pattern.matcher(token).matches();
    }
}
