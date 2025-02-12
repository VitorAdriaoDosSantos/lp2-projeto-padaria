package com.padaria.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private Button buttonAdmin;
    @FXML
    private Button buttonCaixa;

    @FXML
    public void handleButtonAdmin() {
        try {
            Parent newView = FXMLLoader.load(getClass().getResource("/views/application.fxml"));
            Stage stage = (Stage) buttonAdmin.getScene().getWindow();
            stage.setScene(new Scene(newView));
        } catch (Exception e) {
            System.out.println("Erro ao carregar a tela de administração: " + e.getMessage());
        }
    }

    @FXML
    public void handleButtonCaixa() {
        try {
            Parent newView = FXMLLoader.load(getClass().getResource("/views/caixa.fxml"));
            Stage stage = (Stage) buttonCaixa.getScene().getWindow();
            stage.setScene(new Scene(newView));
        } catch (Exception e) {
            System.out.println("Erro ao carregar a tela de caixa: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
