package com.isroy.dev.tokenizer.token.types;

public enum Operator {
    //Conditional Operators
    GREATER_THAN(">",4),
    GREATER_THAN_OR_EQUALS(">=",4),
    LESS_THAN("<", 4),
    LESS_THAN_OR_EQUAL("<=", 4),
    EQUALS("==",3),
    NOT_EQUAL("!=",3),
    BETWEEN("between",2),
    ALL_OF("allof",2),
    NONE_OF("noneof",2),

    //Logical Operators
    AND("and",1),
    OR("or",1);

    String operatorSymbol;
    int precedence;

    Operator(String operatorSymbol, int precedence) {
        this.operatorSymbol = operatorSymbol;
        this.precedence = precedence;
    }

    public boolean isEqual(String token){
        return operatorSymbol.equalsIgnoreCase(token);
    }

    public int getPrecedence() {
        return precedence;
    }
}
