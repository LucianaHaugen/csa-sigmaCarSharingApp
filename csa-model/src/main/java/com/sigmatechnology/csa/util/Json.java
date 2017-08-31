package com.sigmatechnology.csa.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by lucianahaugen on 31/08/17.
 */

public class Json {

    private Json() {
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, classOfT);
        }
        catch (IOException e) {
            return null;
        }
    }

    public static String toJson(Object data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(data);
        }
        catch (JsonProcessingException e) {
            return null;
        }
    }
}
