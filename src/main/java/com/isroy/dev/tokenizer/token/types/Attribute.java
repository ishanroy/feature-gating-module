package com.isroy.dev.tokenizer.token.types;

public enum Attribute {
    NAME("name"),
    AGE("age"),
    CITY("address.city"),
    COUNTRY("address.country"),
    GENDER("gender"),
    PAST_ORDER_AMOUNT("past_order_amount");

    String attributeName;
    Attribute(String attributeName){
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public boolean isValid(String token){
        return attributeName.equalsIgnoreCase(token);
    }
    
    
}
