package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.ApiConfig;
import org.example.dto.ProductListResponse;
import org.example.model.Product;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ProductService {
    private final ApiClient apiClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl = ApiConfig.BASE_URL;

    public ProductService(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.objectMapper = ApiConfig.getObjectMapper();
    }

    public List<Product> list(int limit, int skip) throws Exception {
        String endpoint = String.format("%s/products?limit=%d&skip=%d", baseUrl, limit, skip);
        String jsonResponse = apiClient.get(endpoint);
        ProductListResponse response = objectMapper.readValue(jsonResponse, ProductListResponse.class);
        return response.getProducts();
    }

    public List<Product> search(String query) throws Exception {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String endpoint = String.format("%s/products/search?q=%s", baseUrl, encodedQuery);
        String jsonResponse = apiClient.get(endpoint);
        ProductListResponse response = objectMapper.readValue(jsonResponse, ProductListResponse.class);
        return response.getProducts();
    }
}
