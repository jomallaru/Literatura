package com.literaturachallenge.literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Conversiones {
    private final ObjectMapper objectMapper = new ObjectMapper();
    public <T> T convertirAClase(String json, Class<T> clase){
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
