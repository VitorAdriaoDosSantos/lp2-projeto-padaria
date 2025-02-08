package com.padaria.controller;

import com.padaria.model.dao.DaoFactory;
import com.padaria.model.entities.Produto;
import com.padaria.model.entities.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.Optional;

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
    private TextField textValidade;


    @FXML
    public void initialize() {
        carregarTabela();
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
        LocalDate validade = produto.getValidade();
        if (validade != null) {
            textValidade.setText(validade.toString());
        } else {
            textValidade.setText("null");
        }
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
        try {
            Produto produto = new Produto();
            produto.setId(Integer.parseInt(textId.getText()));
            produto.setNome(textNome.getText());
            produto.setCategoria(textCategoria.getText());
            produto.setPreco(Double.parseDouble(textPreco.getText()));
            produto.setQuantidade(Integer.parseInt(textQtd.getText()));
            produto.setValidade(LocalDate.parse(textValidade.getText()));
            DaoFactory.createProdutoDao().atualizar(produto);
        } catch (Exception e) {
            System.out.println("Erro ao deletar o produto: " + e.getMessage());
        } finally {
            carregarTabela();
        }
    }

    @FXML
    private void adicionarProduto() {
        try {
            Produto produto = new Produto();
            produto.setNome(textNome.getText());
            produto.setCategoria(textCategoria.getText());
            produto.setPreco(Double.parseDouble(textPreco.getText()));
            produto.setQuantidade(Integer.parseInt(textQtd.getText()));
            produto.setValidade(LocalDate.parse(textValidade.getText()));
            DaoFactory.createProdutoDao().inserir(produto);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar o produto: " + e.getMessage());
        } finally {
            carregarTabela();
        }
    }

    @FXML
    private void removerProduto() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Remoção");
        alert.setHeaderText("Remover Produto");
        alert.setContentText("Deseja realmente remover o produto?");

        ButtonType buttonTypeSim = new ButtonType("Sim");
        ButtonType buttonTypeNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeSim, buttonTypeNao);


        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeSim) {
            try {
                int id = Integer.parseInt(textId.getText());
                DaoFactory.createProdutoDao().removerPorId(id);
                carregarTabela();
            } catch (Exception e) {
                System.out.println("Erro ao remover o produto: " + e.getMessage());
            }
        }
        
    }


}
