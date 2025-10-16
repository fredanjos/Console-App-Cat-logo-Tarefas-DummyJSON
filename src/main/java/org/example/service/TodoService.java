package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.ApiConfig;
import org.example.dto.TodoListResponse;
import org.example.model.Todo;

import java.util.List;
import java.util.Map;

public class TodoService {
    private final ApiClient apiClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl = ApiConfig.BASE_URL;

    public TodoService(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.objectMapper = ApiConfig.getObjectMapper();
    }

    public List<Todo> list(int limit, int skip) throws Exception {
        String endpoint = String.format("%s/todos?limit=%d&skip=%d", baseUrl, limit, skip);
        String jsonResponse = apiClient.get(endpoint);
        TodoListResponse response = objectMapper.readValue(jsonResponse, TodoListResponse.class);
        return response.getTodos();
    }

    public Todo add(String text, int userId) throws Exception {
        String endpoint = String.format("%s/todos/add", baseUrl);
        Map<String, Object> todoMap = Map.of(
                "todo", text,
                "completed", false,
                "userId", userId
        );
        String jsonBody = objectMapper.writeValueAsString(todoMap);
        String jsonResponse = apiClient.post(endpoint, jsonBody);
        return objectMapper.readValue(jsonResponse, Todo.class);
    }

    public Todo toggle(int id, boolean completed) throws Exception {
        String endpoint = String.format("%s/todos/%d", baseUrl, id);
        Map<String, Object> bodyMap = Map.of("completed", completed);
        String jsonBody = objectMapper.writeValueAsString(bodyMap);
        String jsonResponse = apiClient.put(endpoint, jsonBody);
        return objectMapper.readValue(jsonResponse, Todo.class);
    }

    public boolean delete(int id) throws Exception {
        String endpoint = String.format("%s/todos/%d", baseUrl, id);
        String jsonResponse = apiClient.delete(endpoint);
        Todo deletedTodo = objectMapper.readValue(jsonResponse, Todo.class);
        return deletedTodo.isDeleted();
    }
}
