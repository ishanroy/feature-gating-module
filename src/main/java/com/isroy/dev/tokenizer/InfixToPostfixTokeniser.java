package com.isroy.dev.tokenizer;

import com.isroy.dev.exception.InvalidExpressionException;
import com.isroy.dev.exception.InvalidTokenException;
import com.isroy.dev.tokenizer.classifier.SimpleTokenClassifier;
import com.isroy.dev.tokenizer.classifier.TokenClassifier;
import com.isroy.dev.tokenizer.token.types.TokenType;
import com.isroy.dev.tokenizer.token.OperatorToken;
import com.isroy.dev.tokenizer.token.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class InfixToPostfixTokeniser implements Tokeniser {

    private TokenClassifier tokenClassifier;
    private List<Token> tokenisedExpression;
    private Stack<Token> stack;

    public InfixToPostfixTokeniser() {
        this.tokenClassifier = new SimpleTokenClassifier();
        this.tokenisedExpression = new ArrayList<>();
        this.stack = new Stack<>();
    }

    @Override
    public List<Token> convert(String expression) throws InvalidExpressionException, InvalidTokenException {

        List<String> exp = Arrays.asList(expression.split(" "));
        if(exp.size() <= 0)
            throw new InvalidExpressionException("The expression is invalid, please check the input format");
        for (int i = 0; i<exp.size(); ++i){
            String tokenValue = exp.get(i);
            Token token = tokenClassifier.getTokenType(tokenValue);
            // If the scanned character is an operand, add it to output.
            if (token.getTokenType() == TokenType.OPERAND || token.getTokenType() == TokenType.ATTRIBUTE )
                tokenisedExpression.add(token);
            // If the scanned character is an '(', push it to the stack.
            else if (token.getTokenType() == TokenType.UNKNOWN && token.getValue().equals("("))
                stack.push(token);
            //  If the scanned character is an ')', pop and output from the stack until an '(' is encountered.
            else if (token.getTokenType() == TokenType.UNKNOWN && token.getValue().equals(")"))
            {
                while (!stack.isEmpty() && !stack.peek().getValue().equals("("))
                    tokenisedExpression.add(stack.pop());
                if (!stack.isEmpty() && !stack.peek().getValue().equals("("))
                    throw new InvalidExpressionException("Invalid infix expression");
                else
                    stack.pop();
            }else if (token.getTokenType() == TokenType.UNKNOWN){
                throw new InvalidTokenException(token.getValue());
            }
            else
            {
                while (!stack.isEmpty() && (stack.peek().getTokenType() == TokenType.OPERATOR) && (((OperatorToken)token).getOperator().getPrecedence() <= ((OperatorToken)stack.peek()).getOperator().getPrecedence())){
                    if(stack.peek().getValue().equals("("))
                        throw new InvalidExpressionException("Invalid infix expression");
                    tokenisedExpression.add(stack.pop());
                }
                stack.push(token);
            }
        }
        // pop all the operators from the stack
        while (!stack.isEmpty()){
            if(stack.peek().getValue().equals("("))
                throw new InvalidExpressionException("Invalid infix expression");
            tokenisedExpression.add(stack.pop());
        }
        return tokenisedExpression;
    }
}
