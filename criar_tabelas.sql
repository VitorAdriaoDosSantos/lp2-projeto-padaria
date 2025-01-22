CREATE DATABASE padaria;

USE padaria;

-- Excluir tabelas existentes
DROP TABLE IF EXISTS venda_produtos, vendas, produtos;

-- Criar tabelas
CREATE TABLE produtos (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    preco DECIMAL(10, 2) NOT NULL,
    quantidade INT NOT NULL,
    validade DATE
);

CREATE TABLE vendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2) NOT NULL
);

CREATE TABLE venda_produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venda_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (venda_id) REFERENCES vendas(id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);

-- Inserir dados na tabela produtos
INSERT INTO produtos (nome, categoria, preco, quantidade, validade) VALUES
('Pão Francês', 'Pães', 0.50, 100, '2025-01-25'),
('Bolo de Chocolate', 'Doces', 15.00, 10, '2025-01-30'),
('Café em Pó', 'Bebidas', 12.50, 20, NULL),
('Leite Integral', 'Bebidas', 5.00, 50, '2025-02-15'),
('Torta de Frango', 'Salgados', 25.00, 5, '2025-01-28');

-- Inserir dados na tabela vendas
INSERT INTO vendas (total) VALUES
(35.00), -- Venda 1
(60.00), -- Venda 2
(12.50); -- Venda 3

-- Produtos vendidos na Venda 1
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(1, 1, 10, 5.00), -- 10 Pães Francês
(1, 2, 2, 30.00); -- 2 Bolos de Chocolate

-- Produtos vendidos na Venda 2
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(2, 1, 20, 10.00), -- 20 Pães Francês
(2, 4, 5, 25.00),  -- 5 Leites Integrais
(2, 5, 1, 25.00);  -- 1 Torta de Frango

-- Produtos vendidos na Venda 3
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(3, 3, 1, 12.50); -- 1 Café em Pó
