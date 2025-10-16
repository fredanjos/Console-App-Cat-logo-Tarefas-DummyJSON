package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.example.model.Product;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductListResponse {
    private List<Product> products;
    private int total;
    private int skip;
    private int limit;
}
