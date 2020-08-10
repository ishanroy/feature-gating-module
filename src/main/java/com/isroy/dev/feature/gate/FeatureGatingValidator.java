package com.isroy.dev.feature.gate;

import com.isroy.dev.evaluator.ConditionalEvaluator;
import com.isroy.dev.evaluator.Evaluator;
import com.isroy.dev.evaluator.mapper.SimpleAttributeMapper;
import com.isroy.dev.exception.EvaluationException;
import com.isroy.dev.exception.InvalidExpressionException;
import com.isroy.dev.exception.InvalidOperandException;
import com.isroy.dev.exception.InvalidTokenException;
import com.isroy.dev.tokenizer.InfixToPostfixTokeniser;
import com.isroy.dev.tokenizer.Tokeniser;
import java.util.Map;

public class FeatureGatingValidator {

    FeatureGatingValidator featureDecider;

    private FeatureGatingValidator() {
    }

    private static class FeatureGatingValidatorHelper{
        private static final FeatureGatingValidator INSTANCE = new FeatureGatingValidator();
    }

    public static FeatureGatingValidator getInstance(){
        return FeatureGatingValidatorHelper.INSTANCE;
    }

    public boolean isValid(String expression, Map<String, Object> user) throws EvaluationException {

        try{
            Tokeniser tokeniser = new InfixToPostfixTokeniser();
            Evaluator<Boolean> evaluator = new ConditionalEvaluator(new SimpleAttributeMapper(user));
            return evaluator.evaluate(tokeniser.convert(expression));
        }catch (InvalidExpressionException e) {
            e.printStackTrace();
            throw new EvaluationException(e);
        } catch (InvalidTokenException e) {
            e.printStackTrace();
            throw new EvaluationException(e);
        } catch (InvalidOperandException e) {
            e.printStackTrace();
            throw new EvaluationException(e);
        }

    }
}
