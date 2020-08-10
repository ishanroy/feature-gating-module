package com.isroy.dev.feature.gate;

import com.isroy.dev.exception.EvaluationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FeatureGatingValidatorTest {

    private static HashMap<String,Object> user;

    @BeforeAll
    public static void initUsers(){

        user = new HashMap<>();
        user.put("name","Ishan");
        user.put("age",26);
        user.put("gender", "Male");
        user.put("past_order_amount", 15000.00);
        user.put("address.city","Bangalore");
        user.put("address.country", "India");

    }

    @ParameterizedTest
    @MethodSource("testExpressions")
    public void featureGatingTest(String expression, boolean expected){
        FeatureGatingValidator featureGatingValidator = FeatureGatingValidator.getInstance();
        try {
            assertEquals(expected, featureGatingValidator.isValid(expression, user));
        } catch (EvaluationException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    private static Stream<Arguments> testExpressions(){
        return Stream.of(
                Arguments.of("age between 25,30", true),
                Arguments.of("age > 25 and gender == \"Male\"", true),
                Arguments.of("age < 30 or address.city == \"Delhi\"",true),
                Arguments.of("age > 30 or address.city == \"Delhi\"",false),
                Arguments.of("age != 26 and past_order_amount between 5000.00,15000.00",false),
                Arguments.of("( age > 25 AND gender == \"Male\" ) OR ( past_order_amount > 10000.0 )", true),
                Arguments.of("( age == 30 AND gender == \"Male\" ) OR ( past_order_amount > 10000.0 )", true),
                Arguments.of("age == 26 and past_order_amount between 5000.00,15000.00 and address.city == \"Bangalore\"",false),
                Arguments.of("address.country == \"India\" and address.city == \"Bangalore\"", true)
                );
    }
}


