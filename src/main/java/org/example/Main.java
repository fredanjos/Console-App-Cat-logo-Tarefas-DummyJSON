package org.example;

import org.example.controller.AppController;
import org.example.service.ApiClient;
import org.example.service.ProductService;
import org.example.service.TodoService;
import org.example.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
    ApiClient apiClient = new ApiClient();
    ConsoleView view = new ConsoleView();

    ProductService productService = new ProductService(apiClient);
    TodoService todoService = new TodoService(apiClient);

    AppController controller = new AppController(view, productService, todoService);

        controller.run();
    }
}