package com.padaria.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private Button buttonEntrar;

    @FXML
    public void Entrar() {
        try {
            Parent newView = FXMLLoader.load(getClass().getResource("/view/application.fxml"));
            Stage stage = (Stage) buttonEntrar.getScene().getWindow();
            stage.setScene(new Scene(newView));
        } catch (Exception e) {
            System.out.println("Erro ao carregar a tela de aplicação: " + e.getMessage());
        }
    }
}
