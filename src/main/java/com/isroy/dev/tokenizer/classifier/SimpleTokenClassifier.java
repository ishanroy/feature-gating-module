package com.isroy.dev.tokenizer.classifier;

import com.isroy.dev.tokenizer.token.types.Attribute;
import com.isroy.dev.tokenizer.token.types.Operand;
import com.isroy.dev.tokenizer.token.types.Operator;
import com.isroy.dev.tokenizer.token.types.TokenType;
import com.isroy.dev.tokenizer.token.AttirbuteToken;
import com.isroy.dev.tokenizer.token.OperandToken;
import com.isroy.dev.tokenizer.token.OperatorToken;
import com.isroy.dev.tokenizer.token.Token;

public class SimpleTokenClassifier implements TokenClassifier{
    private Operator[] operatorsArr;
    private Attribute[] attributesArr;
    private Operand[] operandArr;

    public SimpleTokenClassifier(){
        operatorsArr = Operator.values();
        attributesArr = Attribute.values();
        operandArr = Operand.values();
    }

    public Token getTokenType(String token) {
        for(Operator operator : operatorsArr ){
            if(operator.isEqual(token))
                return new OperatorToken(TokenType.OPERATOR,token, operator);
        }
        for(Attribute attributeType: attributesArr){
            if(attributeType.isValid(token))
                return new AttirbuteToken(TokenType.ATTRIBUTE, token, attributeType);
        }
        for(Operand operandType: operandArr){
            if(operandType.isValid(token))
                return new OperandToken(TokenType.OPERAND, token, operandType );
        }
        return new Token(TokenType.UNKNOWN, token);
    }

}
