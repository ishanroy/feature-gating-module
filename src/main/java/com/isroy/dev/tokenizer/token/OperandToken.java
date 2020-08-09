package com.isroy.dev.tokenizer.token;

import com.isroy.dev.exception.InvalidTokenException;
import com.isroy.dev.tokenizer.token.types.Operand;
import com.isroy.dev.tokenizer.token.types.TokenType;

public class OperandToken extends Token {
    Operand operand;

    public OperandToken(TokenType tokenType, String value, Operand operand) {
        super(tokenType, value);
        this.operand = operand;
    }

    public Operand getOperand() {
        return operand;
    }

    public  Object getOriginalObject() throws InvalidTokenException {
        switch (operand){
            case NUMBER:
                return Integer.parseInt(value);
            case DECIMAL:
                return Double.parseDouble(value);
            case BOOLEAN:
                return Boolean.parseBoolean(value);
            case STRING:
                return value.replaceAll("^[\"']+|[\"']+$", "");
            case NUMBERLIST:
            case DECIMALLIST:
                return value;
            default:
                throw new InvalidTokenException(value);
        }
    }

}
