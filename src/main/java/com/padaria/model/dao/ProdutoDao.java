package com.padaria.model.dao;

import com.padaria.model.entities.Produto;

import java.util.List;

/**
 * Interface responsável pelas operações de persistência da entidade Produto.
 */
public interface ProdutoDao {
    /**
     * Insere um novo produto no banco de dados.
     *
     * @param produto O produto a ser inserido.
     */
    void inserir(Produto produto);

    /**
     * Atualiza um produto existente no banco de dados.
     *
     * @param produto O produto a ser atualizado.
     */
    void atualizar(Produto produto);

    /**
     * Remove um produto do banco de dados pelo seu ID.
     *
     * @param id O ID do produto a ser removido.
     */
    void removerPorId(int id);

    /**
     * Busca um produto no banco de dados pelo seu ID.
     *
     * @param id O ID do produto a ser buscado.
     * @return O produto encontrado ou null se não for encontrado.
     */
    Produto buscarPorId(int id);

    /**
     * Busca todos os produtos no banco de dados.
     *
     * @return Uma lista de todos os produtos.
     */
    List<Produto> buscarTodos();
}