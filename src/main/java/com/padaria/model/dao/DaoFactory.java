package com.padaria.model.dao;

import com.padaria.db.DB;

public interface DaoFactory {
    static ProdutoDao createProdutoDao() {
        return new ProdutoDaoJDBC(DB.getConnection());
    }
    static VendaDao createVendaDao() {
        return new VendaDaoJDBC(DB.getConnection());
    }
}
