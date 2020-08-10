package com.isroy.dev.evaluator.operators;

import com.isroy.dev.exception.InvalidOperandException;

import java.util.List;

public class BetweenEvaluator implements IOperatorEvaluator<Boolean> {

    int numOfOperand = 2;
    Object[] operand2Arr;
    Object operand1;
    Object operand2Lower;
    Object operand2Higher;

    @Override
    public Boolean evaluate(List<Object> operands) throws InvalidOperandException {
        if(validate(operands)){
            setOperands();
            if(operand1 instanceof Integer){
                return (int)operand1 >= (int)operand2Lower && (int)operand1 <= (int)operand2Higher;
            }else if(operand1 instanceof Double)
                return (Double)operand1 >= (Double)operand2Lower && (Double)operand1 <= (Double)operand2Higher;
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
        operand1 = operands.get(1);
        if(operand2 instanceof String){
            String operand2Str = (String)operand2;
            operand2Arr = operand2Str.split(",");
            if(operand2Arr.length != 2)
                return false;
            if(operand1 instanceof Integer || operand1 instanceof Double)
                return true;
        }
        return false;
    }

    private void setOperands(){
        if(operand1 instanceof Integer){
            operand2Lower = Integer.parseInt((String)operand2Arr[0]);
            operand2Higher = Integer.parseInt((String)operand2Arr[1]);
        }else if(operand1 instanceof Double){
            operand2Lower = Double.parseDouble((String)operand2Arr[0]);
            operand2Higher = Double.parseDouble((String)operand2Arr[0]);
        }
    }
}
