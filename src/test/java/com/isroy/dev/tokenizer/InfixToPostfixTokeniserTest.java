package com.isroy.dev.tokenizer;

import com.isroy.dev.exception.InvalidExpressionException;
import com.isroy.dev.exception.InvalidTokenException;
import com.isroy.dev.tokenizer.token.Token;
import org.junit.jupiter.api.Test;

import java.util.List;

public class InfixToPostfixTokeniserTest {

    @Test
    //For basic testing, don't review.
    public void basicTokeniserTest(){
        InfixToPostfixTokeniser tokeniser = new InfixToPostfixTokeniser();
        try {
//          List<Token> tokens = tokeniser.convert("age > 25 AND gender == \"Male\"");
//          List<Token> tokens = tokeniser.convert("( age > 25 AND gender == \"Male\" ) OR ( past_order_amount > 10000 )");
            List<Token> tokens = tokeniser.convert("address.city == \"Bangalore\" and age > 25");

            for(Token token : tokens){
                System.out.println(token.getTokenType() + " " +token.getValue());
            }
        } catch (InvalidExpressionException e) {
            e.printStackTrace();
        } catch (InvalidTokenException e) {
            e.printStackTrace();
        }
    }
}
