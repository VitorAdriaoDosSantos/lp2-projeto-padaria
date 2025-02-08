package com.padaria.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

import java.io.IOException;

public class ApplicationController {

    @FXML
    private Tab tabProdutos;
    @FXML
    private Tab tabVendas;

   public void initialize() {
       try {
           FXMLLoader produtosLoader = new FXMLLoader(getClass().getResource("/view/produtos.fxml"));
           tabProdutos.setContent(produtosLoader.load());
           FXMLLoader vendasLoader = new FXMLLoader(getClass().getResource("/view/vendas.fxml"));
           tabVendas.setContent(vendasLoader.load());
       } catch (IOException e) {
           e.printStackTrace();
       }
   }


}
