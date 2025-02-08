package com.padaria.model.dao;

import com.padaria.db.DB;
import com.padaria.model.entities.Venda;
import com.padaria.model.entities.VendaProdutos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaDaoJDBC implements VendaDao {
    private final Connection conn;

    public VendaDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public List<Venda> listaVendas() {
        PreparedStatement stVendas = null;
        ResultSet rsVendas = null;
        List<Venda> vendas = new ArrayList<>();
        String sqlVendas = "select * from vendas order by data_hora desc";

        try {
            stVendas = conn.prepareStatement(sqlVendas);
            rsVendas = stVendas.executeQuery();
            while (rsVendas.next()) {
                Venda venda = new Venda();
                venda.setId(rsVendas.getInt("id"));
                venda.setData(rsVendas.getTimestamp("data_hora").toLocalDateTime());
                venda.setValorTotal(rsVendas.getDouble("total"));

                // Consulta separada para os itens da venda
                PreparedStatement stItens = null;
                ResultSet rsItens = null;
                String sqlItens = "select * from venda_produtos where venda_id = ?";
                try {
                    stItens = conn.prepareStatement(sqlItens);
                    stItens.setInt(1, venda.getId());
                    rsItens = stItens.executeQuery();
                    List<VendaProdutos> itens = new ArrayList<>();
                    while (rsItens.next()) {
                        VendaProdutos vendaProdutos = new VendaProdutos();
                        vendaProdutos.setId(rsItens.getInt("id"));
                        vendaProdutos.setIdVenda(rsItens.getInt("venda_id"));
                        vendaProdutos.setIdProduto(rsItens.getInt("produto_id"));
                        vendaProdutos.setQuantidade(rsItens.getInt("quantidade"));
                        vendaProdutos.setSubTotal(rsItens.getDouble("subtotal"));
                        vendaProdutos.setProduto(DaoFactory.createProdutoDao().buscarPorId(rsItens.getInt("produto_id")));
                        itens.add(vendaProdutos);
                    }
                    venda.setItens(itens);
                } finally {
                    DB.closeResultSet(rsItens);
                    DB.closeStatement(stItens);
                }

                vendas.add(venda);
            }

            return vendas;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rsVendas);
            DB.closeStatement(stVendas);
        }

        return null;
    }


    @Override
    public void inserir(Venda venda) {

    }

    @Override
    public void deletar(Venda venda) {

    }
}
