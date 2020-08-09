package com.isroy.dev.evaluator.mapper;

import java.util.Map;

public class SimpleAttributeMapper implements AttributeMapper {

    private Map<String,Object> userMap;

    public SimpleAttributeMapper(Map<String,Object> userMap) {
        this.userMap = userMap;
    }

    @Override
    public Object getValue(String attribute){
        return userMap.get(attribute);
    }
}
