package com.padaria.controller;

import com.padaria.model.entities.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class CardProdutoController {
    @FXML private Label produtoNome, produtoPreco, produtoEstoque;
    @FXML private Button buttonAdicionarCarrinho;
    private Produto produto;


    public void setProduto(Produto produto) {
        produtoNome.setText(produto.getNome());
        produtoPreco.setText(String.format("Preço: R$ %.2f", produto.getPreco()));
        produtoEstoque.setText(String.format("Estoque: %d", produto.getQuantidade()));
        this.produto = produto;
    }

    @FXML
    private void handleButtonAdicionarCarrinho() {
        if (produto != null) {
            CaixaController caixaController = CaixaController.getInstance();
            if (caixaController != null) {
                Produto p = new Produto();
                p.setQuantidade(1);
                p.setId(produto.getId());
                p.setNome(produto.getNome());
                p.setPreco(produto.getPreco());
                caixaController.adicionarProduto(p);
            }
        }
    }
}
