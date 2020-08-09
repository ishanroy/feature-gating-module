package com.isroy.dev.evaluator.operators;

import com.isroy.dev.exception.InvalidTokenException;
import com.isroy.dev.tokenizer.token.types.Operator;
import com.isroy.dev.tokenizer.token.OperatorToken;
import com.isroy.dev.tokenizer.token.Token;
import com.isroy.dev.tokenizer.token.types.TokenType;
import jdk.nashorn.internal.runtime.OptimisticReturnFilters;

import java.util.HashMap;

public class OperatorFactory {

    public static HashMap<Operator, IOperatorEvaluator> operatorEvaluatorMap = new HashMap<>();
    static{
            operatorEvaluatorMap.put(Operator.GREATER_THAN, new GreaterThanEvaluator());
            operatorEvaluatorMap.put(Operator.GREATER_THAN_OR_EQUALS, new GreaterThanOrEqualEvaluator());
            operatorEvaluatorMap.put(Operator.LESS_THAN,new LessThanEvaluator());
            operatorEvaluatorMap.put(Operator.LESS_THAN_OR_EQUAL, new LessThanOrEqualEvaluator());
            operatorEvaluatorMap.put(Operator.EQUALS, new EqualsEvaluator());
            operatorEvaluatorMap.put(Operator.NOT_EQUAL, new NotEqualsEvaluator());
            operatorEvaluatorMap.put(Operator.AND,new AndEvaluator());
            operatorEvaluatorMap.put(Operator.OR, new OrEvaluator());
            operatorEvaluatorMap.put(Operator.BETWEEN, new BetweenEvaluator());
    }
    public static IOperatorEvaluator getOperatorEvaluator(Token token) throws InvalidTokenException {
        if(token.getTokenType() != TokenType.OPERATOR)
            throw new InvalidTokenException(token.getValue());
        Operator operator = ((OperatorToken)token).getOperator();
        if(operatorEvaluatorMap.containsKey(operator))
            return operatorEvaluatorMap.get(operator);
        else
            throw new InvalidTokenException(token.getValue());
    }
}
