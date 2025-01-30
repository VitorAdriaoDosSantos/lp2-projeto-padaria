package com.padaria.model.dao;

import com.padaria.db.DB;

public interface DaoFactory {
    public static ProdutoDao createProdutoDao() {
        return new ProdutoDaoJDBC(DB.getConnection());
    }
}
