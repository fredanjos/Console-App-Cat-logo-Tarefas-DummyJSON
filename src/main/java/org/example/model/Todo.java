package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo {
    private int id;
    private String todo;
    private boolean completed;
    private int userId;
    private boolean isDeleted;

    @Override
    public String toString() {
        String status = completed ? "OK" : "PEND";
        return String.format("%d | %s | (%s) | user=%d", id, todo, status, userId);
    }
}
