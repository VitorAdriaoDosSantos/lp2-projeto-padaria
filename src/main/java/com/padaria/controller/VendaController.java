package com.padaria.controller;

import com.padaria.model.dao.DaoFactory;
import com.padaria.model.entities.Produto;
import com.padaria.model.entities.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class VendaController {

    @FXML private TableView<Venda> tabelaVendas;
    @FXML private TableColumn<Venda, Integer> colunaId;
    @FXML private TableColumn<Venda, String> colunaData;
    @FXML private TableColumn<Venda, Double> colunaTotal;

    @FXML
    public void initialize() {
        carregarVendas();
    }

    private void carregarVendas() {
        try{
            ObservableList<Venda> listaVendas = FXCollections.observableList(DaoFactory.createVendaDao().listaVendas());
            colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
            colunaTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
            tabelaVendas.setItems(listaVendas);
        } catch (Exception e) {
            System.out.println("Erro ao carregar a tabela de vendas");
            e.printStackTrace();
        }
    }
}

