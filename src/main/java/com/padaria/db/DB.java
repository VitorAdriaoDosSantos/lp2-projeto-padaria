package com.padaria.db;

import java.sql.*;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados.
 */
public class DB {
    private static Connection conn = null;
    private static final String url = "jdbc:mysql://localhost:3306/padaria";
    private static final String user = "root";
    private static final String password = "6930";

    /**
     * Obtém a conexão com o banco de dados.
     *
     * @return A conexão com o banco de dados.
     * @throws RuntimeException Se não for possível conectar ao banco de dados.
     */
    public static Connection getConnection() {
        if(conn == null) {
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException("Não foi possível conectar ao DB. " + e.getMessage(), e);
            }
        }
        return conn;
    }

    /**
     * Fecha a conexão com o banco de dados.
     *
     * @throws RuntimeException Se ocorrer um erro ao fechar a conexão.
     */
    public static void closeConnection() {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar a conexão com o DB. " + e.getMessage(), e);
            }
        }
    }

    /**
     * Fecha o ResultSet fornecido.
     *
     * @param rs O ResultSet a ser fechado.
     * @throws RuntimeException Se ocorrer um erro ao fechar o ResultSet.
     */
    public static void closeResultSet(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar o ResultSet. " + e.getMessage(), e);
            }
        }
    }

    /**
     * Fecha o Statement fornecido.
     *
     * @param st O Statement a ser fechado.
     * @throws RuntimeException Se ocorrer um erro ao fechar o Statement.
     */
    public static void closeStatement(Statement st){
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar o Statement. " + e.getMessage(), e);
            }
        }
    }
}