package com.padaria;

import com.padaria.view.TabelaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Tabela.fxml"));
            primaryStage.getIcons().add(new Image(getClass().getResource("/icon.png").toString()));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Sistema de Padaria");
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Erro ao carregar a tela principal: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}