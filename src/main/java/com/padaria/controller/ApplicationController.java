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
    @FXML
    private Tab tabRelatorios;

   public void initialize() {
       try {
           FXMLLoader produtosLoader = new FXMLLoader(getClass().getResource("/views/produtos.fxml"));
           tabProdutos.setContent(produtosLoader.load());
           FXMLLoader vendasLoader = new FXMLLoader(getClass().getResource("/views/vendas.fxml"));
           tabVendas.setContent(vendasLoader.load());

           // Temporariamente retirado
           //FXMLLoader relatoriosLoader = new FXMLLoader(getClass().getResource("/views/relatorios.fxml"));
           //tabRelatorios.setContent(relatoriosLoader.load());

       } catch (IOException e) {
           e.printStackTrace();
       }
   }


}
