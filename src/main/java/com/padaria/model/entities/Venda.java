package com.padaria.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Venda {
    private int id;
    private LocalDateTime data;
    private double valorTotal;
    private List<VendaProdutos> itens;

    public Venda() {}

    public int getId() {
        return id;
    }

    public List<VendaProdutos> getItens() {
        return itens;
    }

    public void setItens(List<VendaProdutos> itens) {
        this.itens = itens;
    }


    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", data=" + data +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
