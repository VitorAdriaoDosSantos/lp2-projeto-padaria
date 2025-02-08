package com.padaria.model.dao;

import com.padaria.db.DB;
import com.padaria.model.entities.Venda;

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
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Venda> vendas = new ArrayList<>();
        String sql = "select * from vendas order by data_hora desc";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id"));
                venda.setData(rs.getDate("data_hora").toLocalDate());
                venda.setValorTotal(rs.getDouble("total"));
                vendas.add(venda);
            }

            return vendas;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
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
