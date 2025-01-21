package org.gabz;

import org.gabz.model.Produto;
import org.gabz.model.ProdutoDAO;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        System.out.println("Produtos Cadastrados:");
        for (Produto p : produtoDAO.read()) {
            System.out.println(p.toString());
        }
    }
}