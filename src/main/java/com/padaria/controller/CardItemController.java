package com.padaria.controller;

import com.padaria.model.entities.Produto;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

import javafx.scene.image.ImageView;

public class CardItemController {
    @FXML
    private Label itemNome;
    @FXML
    private Label itemPreco;
    @FXML
    private Label itemQuantidade;
    @FXML
    private Label totalPreco;
    @FXML
    private ImageView imageIncremento;
    @FXML
    private ImageView imageDecremento;
    @FXML
    private ImageView imageRemover;

    private Produto produto;

    public void setItem(Produto produto) {
        this.produto = produto;
        itemNome.setText(produto.getNome());
        itemPreco.setText(String.format("R$ %.2f Cada", produto.getPreco()));
        itemQuantidade.setText(String.valueOf(produto.getQuantidade()));
        totalPreco.setText(String.format("Total: R$ %.2f", produto.getPreco() * produto.getQuantidade()));
    }

    public void handleDecrementarButton() {
        CaixaController caixaController = CaixaController.getInstance();
        caixaController.decrementarItem(produto);
    }

    public void handleIncrementarButton() {
        CaixaController caixaController = CaixaController.getInstance();
        caixaController.incrementarItem(produto);
    }

    public void handleRemoverButton() {
        CaixaController caixaController = CaixaController.getInstance();
        caixaController.removerItem(produto);
    }
}
