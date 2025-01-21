package org.gabz.model;

import org.gabz.controller.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    // Create
    public void create(Produto produto) {
        String sql = "INSERT INTO produtos (nome, categoria, preco, quantidade, validade) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setString(5, produto.getValidade());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar produto: " + e.getMessage(), e);
        }
    }

    // Read
    public List<Produto> read() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setValidade(rs.getString("validade"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler produtos: " + e.getMessage(), e);
        }

        return produtos;
    }

    // Update
    public void update(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, categoria = ?, preco = ?, quantidade = ?, validade = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setString(5, produto.getValidade());
            stmt.setInt(6, produto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage(), e);
        }
    }

    // Delete
    public void delete(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir produto: " + e.getMessage(), e);
        }
    }
}
