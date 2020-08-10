package com.isroy.dev.evaluator;

import com.isroy.dev.evaluator.mapper.SimpleAttributeMapper;
import com.isroy.dev.exception.InvalidExpressionException;
import com.isroy.dev.exception.InvalidOperandException;
import com.isroy.dev.exception.InvalidTokenException;
import com.isroy.dev.tokenizer.InfixToPostfixTokeniser;
import com.isroy.dev.tokenizer.token.Token;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConditonalEvaluatorTest {

    //For basic testing/debugging
    @Test
    public void evaluatorTest() throws InvalidExpressionException, InvalidTokenException, InvalidOperandException {
        InfixToPostfixTokeniser tokeniser = new InfixToPostfixTokeniser();
        List<Token> tokens1 = tokeniser.convert("age > 25 AND gender == \"Male\"");
        List<Token> tokens2 = tokeniser.convert("( age > 25 AND gender == \"Male\" ) OR ( past_order_amount > 10000 )");
        List<Token> tokens3 = tokeniser.convert("age > 25 and address.city == \"Bangalore\"");

        HashMap<String,Object> userAttribute = new HashMap<>();
        userAttribute.put("age",26);
        userAttribute.put("gender", "Male");
        userAttribute.put("past_order_amount", 5000);
        userAttribute.put("address.city", "Bangalore");
        userAttribute.put("address.country", "India");

        ConditionalEvaluator conditionalEvaluator = new ConditionalEvaluator(new SimpleAttributeMapper(userAttribute));
        assertEquals(true,conditionalEvaluator.evaluate(tokens1));
        assertEquals(true,conditionalEvaluator.evaluate(tokens2));
        assertEquals(true,conditionalEvaluator.evaluate(tokens3));

    }
}
