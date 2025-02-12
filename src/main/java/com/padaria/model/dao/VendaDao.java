package com.padaria.model.dao;

import com.padaria.model.entities.Venda;

import java.util.List;

public interface VendaDao {
    List<Venda> listaVendas();
    void inserir(Venda venda);

}
