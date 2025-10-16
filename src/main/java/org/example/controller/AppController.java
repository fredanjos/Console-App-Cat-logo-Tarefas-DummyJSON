package org.example.controller;

import org.example.service.ProductService;
import org.example.service.TodoService;
import org.example.view.ConsoleView;

public class AppController {
    private final ConsoleView view;
    private final ProductService productService;
    private final TodoService todoService;

    public AppController(ConsoleView view, ProductService productService, TodoService todoService) {
        this.view = view;
        this.productService = productService;
        this.todoService = todoService;
    }

    public void run() {
        while (true) {
            view.displayMenu();
            String choice = view.getInput();
            try {
                switch (choice) {
                    case "1": handleListProducts(); break;
                    case "2": handleSearchProducts(); break;
                    case "3": handleListTodos(); break;
                    case "4": handleAddTodo(); break;
                    case "5": handleToggleTodo(); break;
                    case "6": handleDeleteTodo(); break;
                    case "7": view.showMessage("Saindo..."); return;
                    default: view.showError("Opcao invalida!");
                }
            } catch (Exception e) {
                view.showError(e.getMessage());
            }
        }
    }

    private void handleListProducts() throws Exception {
        int limit = view.getInputAsInt("Limite de produtos: ");
        int skip = view.getInputAsInt("Pular quantos produtos (offset): ");
        view.displayProducts(productService.list(limit, skip));
    }

    private void handleSearchProducts() throws Exception {
        String query = view.getInputAsString("Termo de busca: ");
        view.displayProducts(productService.search(query));
    }

    private void handleListTodos() throws Exception {
        int limit = view.getInputAsInt("Limite de tarefas: ");
        int skip = view.getInputAsInt("Pular quantas tarefas (offset): ");
        view.displayTodos(todoService.list(limit, skip));
    }

    private void handleAddTodo() throws Exception {
        String text = view.getInputAsString("Descricao da tarefa: ");
        int userId = view.getInputAsInt("ID do usuario: ");
        var newTodo = todoService.add(text, userId);
        view.showMessage("Tarefa adicionada com sucesso!\n" + newTodo);
    }

    private void handleToggleTodo() throws Exception {
        int id = view.getInputAsInt("ID da tarefa para alterar: ");
        int completedOption = view.getInputAsInt("Marcar como (1-Concluida, 0-Pendente): ");
        boolean isCompleted = completedOption == 1;
        var updatedTodo = todoService.toggle(id, isCompleted);
        view.showMessage("Tarefa atualizada com sucesso!\n" + updatedTodo);
    }

    private void handleDeleteTodo() throws Exception {
        int id = view.getInputAsInt("ID da tarefa para remover: ");
        if (todoService.delete(id)) {
            view.showMessage("Tarefa ID " + id + " removida com sucesso.");
        } else {
            view.showError("Não foi possivel remover a tarefa.");
        }
    }
}
