package com.isroy.dev.evaluator.operators;

import com.isroy.dev.exception.InvalidOperandException;

import java.util.List;

public class NotEqualsEvaluator implements IOperatorEvaluator<Boolean> {

    int numOfOperand = 2;

    @Override
    public Boolean evaluate(List<Object> operands) throws InvalidOperandException {
        if(validate(operands)){
            Object operand1 = operands.get(1);
            Object operand2 = operands.get(0);
            if(operand1 instanceof Integer){
                return (int)operand1 != (int)operand2;
            }else if(operand1 instanceof Double)
                return (double) operand1 != (double) operand2;
            else if(operand1 instanceof Boolean)
                return (boolean) operand1 != (boolean) operand2;
            else if(operand1 instanceof String)
                return !((String)operand1).equals(operand2);
            else
                throw new InvalidOperandException(operands.toString());
        }else
            throw new InvalidOperandException(operands.toString());
    }

    @Override
    public boolean validate(List<Object> operands) {
        if(operands.size() != numOfOperand)
            return false;
        Object operand2 = operands.get(0);
        Object operand1 = operands.get(1);
        if(!operand1.getClass().equals(operand2.getClass()))
            return false;
        if(operand1 instanceof Integer || operand1 instanceof Double || operand1 instanceof String || operand1 instanceof Boolean)
            return true;
        return false;
    }
}
