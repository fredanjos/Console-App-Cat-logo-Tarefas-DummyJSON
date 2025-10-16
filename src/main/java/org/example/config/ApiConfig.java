package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiConfig {
    public static final String BASE_URL = "https://dummyjson.com";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
