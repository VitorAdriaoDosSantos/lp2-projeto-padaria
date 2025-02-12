package com.padaria.model.dao;

import com.padaria.db.DB;
import com.padaria.model.entities.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoJDBC implements ProdutoDao{
    private final Connection conn;

    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Produto produto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO produtos (nome, categoria, preco, quantidade, validade) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, produto.getNome());
            st.setString(2, produto.getCategoria());
            st.setDouble(3, produto.getPreco());
            st.setInt(4, produto.getQuantidade());
            if (produto.getValidade() == null) {
                st.setNull(5, Types.DATE);
            } else {
                st.setDate(5, Date.valueOf(produto.getValidade()));
            }
            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    produto.setId(id);
                } else {
                    throw new RuntimeException("Erro inesperado! Nenhuma linha foi afetada.");
                }
            }

        } catch(SQLException e) {
            throw new RuntimeException("Erro ao inserir um novo produto: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizar(Produto produto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE produtos SET nome = ?, categoria = ?, preco = ?, quantidade = ?, validade = ? WHERE id = ?"
            );
            st.setString(1, produto.getNome());
            st.setString(2, produto.getCategoria());
            st.setDouble(3, produto.getPreco());
            st.setInt(4, produto.getQuantidade());
            st.setDate(5, Date.valueOf(produto.getValidade()));
            st.setInt(6, produto.getId());
            st.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Erro ao deletar um produto: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void removerPorId(int id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM produtos WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Erro ao remover um produto: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Produto buscarPorId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM produtos WHERE id = ?");
            st.setInt(1, id);

            rs = st.executeQuery();
            if(rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                Date validadeDate = rs.getDate("validade");
                if (validadeDate != null) {
                    produto.setValidade(validadeDate.toLocalDate());
                }

                return produto;
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Produto> buscarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM produtos");
            rs = st.executeQuery();

            List<Produto> produtos = new ArrayList<>();

            while(rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                Date validadeDate = rs.getDate("validade");
                if (validadeDate != null) {
                    produto.setValidade(validadeDate.toLocalDate());
                }
                produtos.add(produto);
            }

            return produtos;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os produtos: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }
}
