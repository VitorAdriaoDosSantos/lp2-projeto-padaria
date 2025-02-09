CREATE DATABASE padaria;
USE padaria;

-- Excluir tabelas existentes (na ordem de dependência)
DROP TABLE IF EXISTS venda_produtos, vendas, produtos;

-- Criar tabela de produtos
CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    preco DECIMAL(10, 2) NOT NULL,
    quantidade INT NOT NULL,
    validade DATE
);

-- Criar tabela de vendas
CREATE TABLE vendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2) NOT NULL
);

-- Criar tabela de venda_produtos
CREATE TABLE venda_produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venda_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (venda_id) REFERENCES vendas(id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);

---------------------------------------------
-- Inserir diversos produtos na tabela produtos
---------------------------------------------
INSERT INTO produtos (nome, categoria, preco, quantidade, validade) VALUES
-- Produtos já existentes
('Pão Francês', 'Pães', 0.50, 100, '2025-01-25'),
('Bolo de Chocolate', 'Doces', 15.00, 10, '2025-01-30'),
('Café em Pó', 'Bebidas', 12.50, 20, NULL),
('Leite Integral', 'Bebidas', 5.00, 50, '2025-02-15'),
('Torta de Frango', 'Salgados', 25.00, 5, '2025-01-28'),
-- Novos produtos
('Pão Integral', 'Pães', 1.00, 80, '2025-01-27'),
('Bolo de Cenoura', 'Doces', 18.00, 8, '2025-02-05'),
('Pão de Queijo', 'Salgados', 0.80, 150, '2025-01-26'),
('Empada de Frango', 'Salgados', 5.00, 60, '2025-01-29'),
('Croissant', 'Pães', 3.50, 40, '2025-01-24'),
('Muffin Blueberry', 'Doces', 4.00, 30, '2025-01-31'),
('Bolo de Morango', 'Doces', 20.00, 6, '2025-02-10'),
('Sanduíche Natural', 'Salgados', 10.00, 25, '2025-01-28'),
('Suco de Laranja', 'Bebidas', 7.00, 35, '2025-02-20'),
('Água Mineral', 'Bebidas', 2.00, 100, '2025-03-01'),
('Biscoito de Polvilho', 'Salgados', 3.00, 200, '2025-02-15'),
('Pão de Batata', 'Pães', 1.20, 90, '2025-01-29'),
('Quiche de Alho-Poró', 'Salgados', 12.00, 15, '2025-02-08'),
('Bolo de Fubá', 'Doces', 16.00, 12, '2025-02-03'),
('Pão de Milho', 'Pães', 0.70, 110, '2025-01-27'),
('Empada de Palmito', 'Salgados', 6.50, 50, '2025-02-12'),
('Cookie', 'Doces', 2.50, 120, '2025-03-05');

---------------------------------------------
-- Inserir diversas vendas na tabela vendas
---------------------------------------------
INSERT INTO vendas (data_hora, total) VALUES
('2025-01-20 08:15:00', 35.00),   -- Venda 1
('2025-01-20 10:30:00', 60.00),   -- Venda 2
('2025-01-20 12:45:00', 12.50),   -- Venda 3
('2025-01-21 09:10:00', 48.00),   -- Venda 4
('2025-01-21 14:20:00', 75.50),   -- Venda 5
('2025-01-22 11:05:00', 22.00),   -- Venda 6
('2025-01-22 16:30:00', 55.00),   -- Venda 7
('2025-01-23 07:50:00', 33.00),   -- Venda 8
('2025-01-23 13:15:00', 80.00),   -- Venda 9
('2025-01-24 15:40:00', 45.00),   -- Venda 10
('2025-01-25 08:00:00', 90.00),   -- Venda 11
('2025-01-25 17:25:00', 65.00),   -- Venda 12
('2025-01-26 10:45:00', 40.00),   -- Venda 13
('2025-01-26 12:30:00', 50.00),   -- Venda 14
('2025-01-27 09:20:00', 85.00),   -- Venda 15
('2025-01-27 16:00:00', 70.00),   -- Venda 16
('2025-01-28 11:30:00', 55.50),   -- Venda 17
('2025-01-28 14:10:00', 68.00),   -- Venda 18
('2025-01-29 10:20:00', 95.00),   -- Venda 19
('2025-01-29 17:45:00', 60.00);   -- Venda 20

---------------------------------------------
-- Inserir diversos registros na tabela venda_produtos
---------------------------------------------
-- Venda 1
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(1, 1, 10, 5.00),   -- 10 Pães Francês
(1, 2, 2, 30.00);   -- 2 Bolos de Chocolate

-- Venda 2
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(2, 1, 20, 10.00),  -- 20 Pães Francês
(2, 4, 5, 25.00),
(2, 5, 1, 25.00);   -- 1 Torta de Frango

-- Venda 3
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(3, 3, 1, 12.50);   -- 1 Café em Pó

-- Venda 4
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(4, 6, 15, 15.00),  -- 15 Pães Integrais
(4, 8, 10, 8.00),   -- 10 Pão de Queijo
(4, 9, 5, 25.00);   -- 5 Empadas de Frango

-- Venda 5
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(5, 7, 3, 54.00),   -- 3 Bolos de Cenoura (3 x 18.00)
(5, 10, 4, 14.00),  -- 4 Croissants (4 x 3.50)
(5, 11, 2, 8.00);   -- 2 Muffins Blueberry (2 x 4.00)

-- Venda 6
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(6, 12, 1, 20.00),  -- 1 Bolo de Morango
(6, 13, 2, 20.00),  -- 2 Sanduíches Naturais (2 x 10.00)
(6, 1, 5, 2.50);    -- 5 Pães Francês (5 x 0.50)

-- Venda 7
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(7, 14, 4, 28.00),  -- 4 Sucos de Laranja (4 x 7.00)
(7, 15, 10, 20.00), -- 10 Águas Minerais (10 x 2.00)
(7, 16, 6, 18.00);  -- 6 Biscoitos de Polvilho (6 x 3.00)

-- Venda 8
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(8, 17, 8, 9.60),   -- 8 Pães de Batata (8 x 1.20)
(8, 18, 2, 24.00),  -- 2 Quiches de Alho-Poró (2 x 12.00)
(8, 1, 15, 7.50);   -- 15 Pães Francês (15 x 0.50)

-- Venda 9
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(9, 2, 1, 15.00),   -- 1 Bolo de Chocolate
(9, 7, 2, 36.00),   -- 2 Bolos de Cenoura (2 x 18.00)
(9, 13, 3, 30.00),  -- 3 Sanduíches Naturais (3 x 10.00)
(9, 15, 5, 10.00);  -- 5 Águas Minerais (5 x 2.00)

-- Venda 10
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(10, 3, 2, 25.00),  -- 2 Cafés em Pó (2 x 12.50)
(10, 4, 3, 15.00),  -- 3 Leites Integrais (3 x 5.00)
(10, 16, 4, 12.00); -- 4 Biscoitos de Polvilho (4 x 3.00)

-- Venda 11
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(11, 11, 5, 20.00),  -- 5 Muffins Blueberry (5 x 4.00)
(11, 12, 2, 40.00),  -- 2 Bolos de Morango (2 x 20.00)
(11, 15, 15, 30.00); -- 15 Águas Minerais (15 x 2.00)

-- Venda 12
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(12, 1, 30, 15.00),   -- 30 Pães Francês (30 x 0.50)
(12, 2, 1, 15.00),    -- 1 Bolo de Chocolate (1 x 15.00)
(12, 8, 20, 16.00);   -- 20 Pães de Queijo (20 x 0.80)

-- Venda 13
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(13, 5, 1, 25.00),    -- 1 Torta de Frango
(13, 10, 3, 10.50),   -- 3 Croissants (3 x 3.50)
(13, 7, 1, 18.00);    -- 1 Bolo de Cenoura

-- Venda 14
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(14, 17, 10, 12.00),  -- 10 Pães de Batata (10 x 1.20)
(14, 14, 3, 21.00),   -- 3 Sucos de Laranja (3 x 7.00)
(14, 15, 5, 10.00);   -- 5 Águas Minerais (5 x 2.00)

-- Venda 15
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(15, 19, 2, 32.00),   -- 2 Bolos de Fubá (2 x 16.00)
(15, 20, 4, 2.80),    -- 4 Pães de Milho (4 x 0.70)
(15, 13, 2, 20.00);   -- 2 Sanduíches Naturais (2 x 10.00)

-- Venda 16
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(16, 9, 4, 20.00),    -- 4 Empadas de Frango (4 x 5.00)
(16, 8, 6, 4.80),     -- 6 Pães de Queijo (6 x 0.80)
(16, 1, 10, 5.00);    -- 10 Pães Francês (10 x 0.50)

-- Venda 17
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(17, 11, 4, 16.00),   -- 4 Muffins Blueberry (4 x 4.00)
(17, 12, 1, 20.00),   -- 1 Bolo de Morango (1 x 20.00)
(17, 15, 10, 20.00);  -- 10 Águas Minerais (10 x 2.00)

-- Venda 18
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(18, 2, 2, 30.00),    -- 2 Bolos de Chocolate (2 x 15.00)
(18, 7, 1, 18.00),    -- 1 Bolo de Cenoura (1 x 18.00)
(18, 13, 4, 40.00);   -- 4 Sanduíches Naturais (4 x 10.00)

-- Venda 19
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(19, 4, 10, 50.00),   -- 10 Leites Integrais (10 x 5.00)
(19, 16, 6, 18.00),   -- 6 Biscoitos de Polvilho (6 x 3.00)
(19, 17, 5, 6.00);    -- 5 Pães de Batata (5 x 1.20)

-- Venda 20
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
(20, 14, 5, 35.00),   -- 5 Sucos de Laranja (5 x 7.00)
(20, 15, 10, 20.00),  -- 10 Águas Minerais (10 x 2.00)
(20, 20, 3, 2.10);    -- 3 Pães de Milho (3 x 0.70)
