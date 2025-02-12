package com.padaria.util;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ConfirmationDialog {

    private static boolean result = false;

    // Método para exibir a caixa de diálogo de confirmação
    public static boolean show(Stage owner, String message) {
        Platform.runLater(() -> createDialog(owner, message));
        return result;
    }

    // Método privado para criar e exibir a caixa de diálogo
    private static void createDialog(Stage owner, String message) {
        Stage stage = new Stage();
        stage.initOwner(owner); // Define o Stage principal como dono
        stage.initModality(Modality.APPLICATION_MODAL); // Bloqueia interação com a janela principal
        stage.initStyle(StageStyle.UTILITY); // Estilo mais adequado para o tipo de janela
        stage.setResizable(false); // Impede o redimensionamento da janela

        // Configuração do Label (mensagem da caixa de diálogo)
        Label label = new Label(message);
        label.setStyle("-fx-font-size: 16px; -fx-text-fill: #333; -fx-wrap-text: true;");
        label.setMaxWidth(350); // Limita a largura da mensagem

        // Botões de confirmação (Sim e Não)
        Button btnYes = new Button("Sim");
        btnYes.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 16px;");
        btnYes.setMinWidth(80); // Aumenta o tamanho do botão
        btnYes.setOnAction(e -> {
            result = true;
            stage.close();
        });

        Button btnNo = new Button("Não");
        btnNo.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-size: 16px;");
        btnNo.setMinWidth(80); // Aumenta o tamanho do botão
        btnNo.setOnAction(e -> {
            result = false;
            stage.close();
        });

        // Layout dos botões
        HBox buttonBox = new HBox(15, btnYes, btnNo);
        buttonBox.setAlignment(Pos.CENTER);

        // Layout principal da caixa de diálogo
        VBox root = new VBox(25, label, buttonBox);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-border-radius: 12;");

        // Adiciona um efeito de sombra
        StackPane container = new StackPane(root);
        container.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0.5, 0, 1);");

        // Configuração da cena
        Scene scene = new Scene(container);
        scene.setFill(null); // Cena transparente

        stage.setScene(scene);
        stage.sizeToScene();

        // Chama a centralização após o layout ser gerado
        Platform.runLater(() -> positionStage(stage, owner));

        stage.showAndWait(); // Exibe a caixa de diálogo e aguarda uma resposta
    }

    // Método para centralizar corretamente o Stage da caixa de diálogo
    private static void positionStage(Stage stage, Stage owner) {
        double ownerX = owner.getX();
        double ownerY = owner.getY();
        double ownerWidth = owner.getWidth();
        double ownerHeight = owner.getHeight();

        // Obtém o tamanho da caixa de diálogo
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();

        // Centraliza o Stage da caixa de diálogo em relação ao Stage principal
        stage.setX(ownerX + (ownerWidth - stageWidth) / 2);
        stage.setY(ownerY + (ownerHeight - stageHeight) / 2);
    }
}
