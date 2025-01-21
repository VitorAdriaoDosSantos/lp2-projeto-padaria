package com.padaria;

import com.padaria.model.Produto;
import com.padaria.model.ProdutoDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Cria uma lista observável de produtos a partir dos produtos do banco de dados
        ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutoDAO().read());


        TableView<Produto> tableView = new TableView<>(produtos);
        tableView.getColumns().addAll(
                createColumn("ID", "id", 50),
                createColumn("Nome", "nome", 100),
                createColumn("Categoria", "categoria", 100),
                createColumn("Preço", "preco", 50),
                createColumn("QTD", "quantidade", 50),
                createColumn("Validade", "validade", 100)
        );

        StackPane root = new StackPane(tableView);
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.setTitle("Sistema da Padaria");
        primaryStage.show();
    }

    private <T> TableColumn<Produto, T> createColumn(String title, String property, int width) {
        TableColumn<Produto, T> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setPrefWidth(width);
        return column;
    }

    public static void main(String[] args) {
        launch(args);
    }
}