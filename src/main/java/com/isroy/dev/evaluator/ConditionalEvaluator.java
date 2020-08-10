package com.isroy.dev.evaluator;

import com.isroy.dev.evaluator.mapper.AttributeMapper;
import com.isroy.dev.evaluator.operators.IOperatorEvaluator;
import com.isroy.dev.evaluator.operators.OperatorFactory;
import com.isroy.dev.exception.InvalidOperandException;
import com.isroy.dev.exception.InvalidTokenException;
import com.isroy.dev.tokenizer.token.types.TokenType;
import com.isroy.dev.tokenizer.token.OperandToken;
import com.isroy.dev.tokenizer.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ConditionalEvaluator implements Evaluator<Boolean> {
    private Stack<Object> stack;
    private AttributeMapper attributeMapper;

    public ConditionalEvaluator(AttributeMapper attributeMapper) {
        this.attributeMapper = attributeMapper;
        this.stack = new Stack<>();
    }

    @Override
    public Boolean evaluate(List<Token> tokenisedExp) throws InvalidTokenException, InvalidOperandException {

        // Scan all characters one by one
        for(int i=0;i<tokenisedExp.size();i++) {
            Token token = tokenisedExp.get(i);
            // If the scanned character is an operand (number here),
            // push it to the stack.
            if(token.getTokenType() == TokenType.OPERAND){
                stack.push(((OperandToken)token).getOriginalObject());
            }else if(token.getTokenType() == TokenType.ATTRIBUTE){
                stack.push(attributeMapper.getValue(token.getValue()));
            }
            //  If the scanned character is an operator, pop two
            // elements from stack apply the operator
            else if(token.getTokenType() == TokenType.OPERATOR)
            {
                List<Object> operands = new ArrayList<>();
                operands.add(stack.pop());
                operands.add(stack.pop());
                IOperatorEvaluator evaluator = OperatorFactory.getOperatorEvaluator(token);
                stack.push(evaluator.evaluate(operands));
            }
        }
        return (boolean)stack.pop();
    }

}
