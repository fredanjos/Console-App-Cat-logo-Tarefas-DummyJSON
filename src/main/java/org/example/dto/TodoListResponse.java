package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.example.model.Product;
import org.example.model.Todo;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoListResponse {
    private List<Todo> todos;
    private int total;
    private int skip;
    private int limit;
}
