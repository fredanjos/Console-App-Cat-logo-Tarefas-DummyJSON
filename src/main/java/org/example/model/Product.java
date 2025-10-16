package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private int id;
    private String title;
    private double price;

    @Override
    public String toString() {
        return String.format("%d | %s | R$ %.2f", id, title, price);
    }
}
