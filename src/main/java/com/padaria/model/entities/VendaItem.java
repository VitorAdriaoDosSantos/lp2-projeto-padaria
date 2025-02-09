package com.padaria.model.entities;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class VendaItem {
    public final SimpleIntegerProperty id, idProduto, qtd;
    public final SimpleObjectProperty<LocalDateTime> data;
    public final SimpleDoubleProperty total, precoProduto, subtotal;
    public final SimpleStringProperty nomeProduto, categoriaProduto;

    public VendaItem(int id, LocalDateTime data, double total) {
        this.id = new SimpleIntegerProperty(id);
        this.data = new SimpleObjectProperty<>(data);
        this.total = new SimpleDoubleProperty(total);
        this.idProduto = new SimpleIntegerProperty(0);
        this.nomeProduto = new SimpleStringProperty("");
        this.categoriaProduto = new SimpleStringProperty("");
        this.precoProduto = new SimpleDoubleProperty(0);
        this.qtd = new SimpleIntegerProperty(0);
        this.subtotal = new SimpleDoubleProperty(0);
    }

    public VendaItem(int idProduto, String nomeProduto, String categoriaProduto, double precoProduto, int qtd, double subtotal) {
        this.id = new SimpleIntegerProperty(0);
        this.data = new SimpleObjectProperty<>(null);
        this.total = new SimpleDoubleProperty(0);
        this.idProduto = new SimpleIntegerProperty(idProduto);
        this.nomeProduto = new SimpleStringProperty(nomeProduto);
        this.categoriaProduto = new SimpleStringProperty(categoriaProduto);
        this.precoProduto = new SimpleDoubleProperty(precoProduto);
        this.qtd = new SimpleIntegerProperty(qtd);
        this.subtotal = new SimpleDoubleProperty(subtotal);
    }
}