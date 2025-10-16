package org.example.service;

import org.example.exception.ApiException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiClient {
    private String makeRequest(String method, String endpoint, String jsonBody) throws Exception {
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");

        if (jsonBody != null && !jsonBody.isEmpty()) {
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }

        int responseCode = conn.getResponseCode();

        if (responseCode >= 400 && responseCode < 500) {
            String errorMessage = "O recurso nao foi encontrado ou os dados enviados sao invalidos.";
            throw new ApiException(errorMessage + " (Status code: " + responseCode + ")");
        } else if (responseCode >= 500 && responseCode < 600) {
            String errorMessage = "Erro interno no servidor da API. Tente novamente mais tarde.";
            throw new ApiException(errorMessage + " (Status code: " + responseCode + ")");
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line.trim());
            }
        }
        conn.disconnect();
        return response.toString();
    }

    public String get(String endpoint) throws Exception {
        return makeRequest("GET", endpoint, null);
    }

    public String post(String endpoint, String jsonBody) throws Exception {
        return makeRequest("POST", endpoint, jsonBody);
    }

    public String put(String endpoint, String jsonBody) throws Exception {
        return makeRequest("PUT", endpoint, jsonBody);
    }

    public String delete(String endpoint) throws Exception {
        return makeRequest("DELETE", endpoint, null);
    }
}
