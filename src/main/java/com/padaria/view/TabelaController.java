package com.padaria.view;

import com.padaria.model.Produto;
import com.padaria.model.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabelaController {
    ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutoDAO().read());

    @FXML
    private TableView<Produto> tabelaProdutos;
    @FXML
    private TableColumn<Produto, Integer> id;
    @FXML
    private TableColumn<Produto, String> nome;
    @FXML
    private TableColumn<Produto, Double> preco;
    @FXML
    private TableColumn<Produto, Integer> quantidade;
    @FXML
    private TableColumn<Produto, String> categoria;
    @FXML
    private TableColumn<Produto, String> validade;

    @FXML
    public void initialize(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        preco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        validade.setCellValueFactory(new PropertyValueFactory<>("validade"));

        tabelaProdutos.setItems(produtos);
    }

}
