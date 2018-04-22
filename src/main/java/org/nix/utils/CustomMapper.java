package org.nix.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomMapper extends ObjectMapper{
    public CustomMapper(){
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
    }
}
