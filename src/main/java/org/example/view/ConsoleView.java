package org.example.view;

import org.example.model.Product;
import org.example.model.Todo;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n===== Catalogo & Tarefas =====");
        System.out.println("1. Listar produtos");
        System.out.println("2. Buscar produtos por texto");
        System.out.println("3. Listar tarefas");
        System.out.println("4. Adicionar tarefa");
        System.out.println("5. Marcar/Desmarcar tarefa");
        System.out.println("6. Remover tarefa");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opcao: ");
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public int getInputAsInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada invalida. Por favor, insira um numero: ");
            scanner.next();
        }
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public String getInputAsString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println("\n>> " + message);
    }

    public void showError(String message) {
        System.err.println("\n!! ERRO: " + message);
    }

    public void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            showMessage("Nenhum produto encontrado.");
            return;
        }
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        products.forEach(System.out::println);
        System.out.println("-------------------------");
    }

    public void displayTodos(List<Todo> todos) {
        if (todos.isEmpty()) {
            showMessage("Nenhuma tarefa encontrada.");
            return;
        }
        System.out.println("\n--- LISTA DE TAREFAS ---");
        todos.forEach(System.out::println);
        System.out.println("------------------------");
    }
}
