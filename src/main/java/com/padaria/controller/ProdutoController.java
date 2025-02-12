package com.padaria.controller;

import com.padaria.model.dao.DaoFactory;
import com.padaria.model.entities.Produto;
import com.padaria.util.ConfirmationDialog;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ProdutoController {

    @FXML
    private TableView<Produto> tabelaProdutos;
    @FXML
    private TableColumn<Produto, Integer> colunaId;
    @FXML
    private TableColumn<Produto, String> colunaNome;
    @FXML
    private TableColumn<Produto, String> colunaCategoria;
    @FXML
    private TableColumn<Produto, Double> colunaPreco;
    @FXML
    private TableColumn<Produto, Integer> colunaQtd;
    @FXML
    private TableColumn<Produto, String> colunaValidade;
    @FXML
    private TextField textId;
    @FXML
    private TextField textNome;
    @FXML
    private TextField textCategoria;
    @FXML
    private TextField textPreco;
    @FXML
    private TextField textQtd;
    @FXML
    private DatePicker dataValidade;

    private Stage stage;
    @FXML
    public void initialize() {
        carregarTabela();
        Platform.runLater(() -> stage = (Stage) tabelaProdutos.getScene().getWindow());
    }

    @FXML
    public void handleMouseClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Produto produtoSelecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
            if (produtoSelecionado != null) {
                carregarDetalhesProduto(produtoSelecionado);
            }
        }
    }

    private void carregarDetalhesProduto(Produto produto) {
        textId.setText(String.valueOf(produto.getId()));
        textNome.setText(produto.getNome());
        textCategoria.setText(produto.getCategoria());
        textPreco.setText(String.valueOf(produto.getPreco()));
        textQtd.setText(String.valueOf(produto.getQuantidade()));
        dataValidade.setValue(produto.getValidade());
    }

    private void carregarTabela() {
        try {
            ObservableList<Produto> observableList = FXCollections.observableList(DaoFactory.createProdutoDao().buscarTodos());
            colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
            colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
            colunaQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            colunaValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));
            tabelaProdutos.setItems(observableList);
        } catch (Exception e) {
            System.out.println("Erro ao carregar a tabela de produtos:");
            e.printStackTrace();
        }
    }




    @FXML
    private void atualizarProduto() {
        boolean confimacao = ConfirmationDialog.show(stage, "Tem certeza que deseja atualizar o produto?");
        if (confimacao) {
            try {
                Produto produto = new Produto();
                produto.setId(Integer.parseInt(textId.getText()));
                produto.setNome(textNome.getText());
                produto.setCategoria(textCategoria.getText());
                produto.setPreco(Double.parseDouble(textPreco.getText()));
                produto.setQuantidade(Integer.parseInt(textQtd.getText()));
                produto.setValidade(dataValidade.getValue());
                DaoFactory.createProdutoDao().atualizar(produto);
            } catch (Exception e) {
                System.out.println("Erro ao deletar o produto: " + e.getMessage());
            } finally {
                carregarTabela();
            }
        }
    }

    @FXML
    private void adicionarProduto() {
        boolean confirmacao = ConfirmationDialog.show(stage, "Tem certeza que deseja adicionar o produto?");
        if (confirmacao){
            try {
                Produto produto = new Produto();
                produto.setNome(textNome.getText());
                produto.setCategoria(textCategoria.getText());
                produto.setPreco(Double.parseDouble(textPreco.getText()));
                produto.setQuantidade(Integer.parseInt(textQtd.getText()));
                produto.setValidade(dataValidade.getValue());
                DaoFactory.createProdutoDao().inserir(produto);
            } catch (Exception e) {
                System.out.println("Erro ao adicionar o produto: " + e.getMessage());
            } finally {
                carregarTabela();
            }
        }

    }

    @FXML
    private void removerProduto() {
        boolean confirmacao = ConfirmationDialog.show(stage, "Tem certeza que deseja remover o produto?");

        if (confirmacao) {
            try {
                int id = Integer.parseInt(textId.getText());
                DaoFactory.createProdutoDao().removerPorId(id);
                carregarTabela();
            } catch (Exception e) {
                System.out.println("Erro ao remover o produto: " + e.getMessage());
            }
        } else {
            System.out.println("Remoção cancelada pelo usuário.");
        }
        
    }


}
