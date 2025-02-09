USE padaria;

-- INSERÇÃO DAS VENDAS COM DATAS VARIADAS (TODAS ≤ '2025-02-09') 
-- e totais ajustados para corresponder à soma dos subtotais nos detalhes:

INSERT INTO vendas (data_hora, total) VALUES
    ('2023-05-10 08:15:00', 35.00),    -- Venda 1
    ('2023-06-22 10:30:00', 60.00),    -- Venda 2
    ('2023-07-15 12:45:00', 12.50),    -- Venda 3
    ('2023-08-05 09:10:00', 48.00),    -- Venda 4
    ('2023-09-12 14:20:00', 76.00),    -- Venda 5 (76.00 = 54.00+14.00+8.00)
    ('2023-10-03 11:05:00', 42.50),    -- Venda 6 (42.50 = 20.00+20.00+2.50)
    ('2023-10-21 16:30:00', 66.00),    -- Venda 7 (66.00 = 28.00+20.00+18.00)
    ('2023-11-10 07:50:00', 41.10),    -- Venda 8 (41.10 = 9.60+24.00+7.50)
    ('2023-11-28 13:15:00', 91.00),    -- Venda 9 (91.00 = 15.00+36.00+30.00+10.00)
    ('2023-12-05 15:40:00', 52.00),    -- Venda 10 (52.00 = 25.00+15.00+12.00)
    ('2024-01-15 08:00:00', 90.00),    -- Venda 11
    ('2024-02-10 17:25:00', 46.00),    -- Venda 12 (46.00 = 15.00+15.00+16.00)
    ('2024-03-08 10:45:00', 53.50),    -- Venda 13 (53.50 = 25.00+10.50+18.00)
    ('2024-04-12 12:30:00', 43.00),    -- Venda 14 (43.00 = 12.00+21.00+10.00)
    ('2024-05-20 09:20:00', 54.80),    -- Venda 15 (54.80 = 32.00+2.80+20.00)
    ('2024-06-15 16:00:00', 29.80),    -- Venda 16 (29.80 = 20.00+4.80+5.00)
    ('2024-07-04 11:30:00', 56.00),    -- Venda 17 (56.00 = 16.00+20.00+20.00)
    ('2024-08-18 14:10:00', 88.00),    -- Venda 18 (88.00 = 30.00+18.00+40.00)
    ('2024-09-25 10:20:00', 74.00),    -- Venda 19 (74.00 = 50.00+18.00+6.00)
    ('2024-10-30 17:45:00', 57.10),    -- Venda 20 (57.10 = 35.00+20.00+2.10)
    ('2024-11-25 19:00:00', 330.00),   -- Venda 21 (330.00 = 250.00+80.00)
    ('2024-12-10 14:30:00', 75.50),    -- Venda 22 (75.50 = 10.00+25.00+8.00+12.00+8.00+12.50)
    ('2025-01-02 09:45:00', 200.00),   -- Venda 23 (200.00 = 2×100.00)
    ('2025-01-30 17:00:00', 350.00),   -- Venda 24 (350.00 = 150.00+200.00)
    ('2023-04-20 08:00:00', 25.00),    -- Venda 25
    ('2022-11-11 12:30:00', 25.00),    -- Venda 26
    ('2024-01-05 00:00:00', 50.00),    -- Venda 27
    ('2024-12-01 18:30:00', 100.00),   -- Venda 28
    ('2025-02-08 13:15:00', 110.00),   -- Venda 29 (110.00 = 36.00+40.00+20.00+6.00+8.00)
    ('2023-03-17 16:45:00', 60.00);    -- Venda 30

--------------------------------------------------------------------------------
-- INSERÇÃO DOS DETALHES (venda_produtos) – as referências de venda_id devem
-- corresponder à ordem de inserção acima.
--------------------------------------------------------------------------------

-- Venda 1
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (1, 1, 10, 5.00),
    (1, 2, 2, 30.00);

-- Venda 2
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (2, 1, 20, 10.00),
    (2, 4, 5, 25.00),
    (2, 5, 1, 25.00);

-- Venda 3
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (3, 3, 1, 12.50);

-- Venda 4
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (4, 6, 15, 15.00),
    (4, 8, 10, 8.00),
    (4, 9, 5, 25.00);

-- Venda 5
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (5, 7, 3, 54.00),
    (5, 10, 4, 14.00),
    (5, 11, 2, 8.00);

-- Venda 6
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (6, 12, 1, 20.00),
    (6, 13, 2, 20.00),
    (6, 1, 5, 2.50);

-- Venda 7
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (7, 14, 4, 28.00),
    (7, 15, 10, 20.00),
    (7, 16, 6, 18.00);

-- Venda 8
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (8, 17, 8, 9.60),
    (8, 18, 2, 24.00),
    (8, 1, 15, 7.50);

-- Venda 9
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (9, 2, 1, 15.00),
    (9, 7, 2, 36.00),
    (9, 13, 3, 30.00),
    (9, 15, 5, 10.00);

-- Venda 10
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (10, 3, 2, 25.00),
    (10, 4, 3, 15.00),
    (10, 16, 4, 12.00);

-- Venda 11
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (11, 11, 5, 20.00),
    (11, 12, 2, 40.00),
    (11, 15, 15, 30.00);

-- Venda 12
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (12, 1, 30, 15.00),
    (12, 2, 1, 15.00),
    (12, 8, 20, 16.00);

-- Venda 13
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (13, 5, 1, 25.00),
    (13, 10, 3, 10.50),
    (13, 7, 1, 18.00);

-- Venda 14
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (14, 17, 10, 12.00),
    (14, 14, 3, 21.00),
    (14, 15, 5, 10.00);

-- Venda 15
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (15, 19, 2, 32.00),
    (15, 20, 4, 2.80),
    (15, 13, 2, 20.00);

-- Venda 16
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (16, 9, 4, 20.00),
    (16, 8, 6, 4.80),
    (16, 1, 10, 5.00);

-- Venda 17
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (17, 11, 4, 16.00),
    (17, 12, 1, 20.00),
    (17, 15, 10, 20.00);

-- Venda 18
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (18, 2, 2, 30.00),
    (18, 7, 1, 18.00),
    (18, 13, 4, 40.00);

-- Venda 19
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (19, 4, 10, 50.00),
    (19, 16, 6, 18.00),
    (19, 17, 5, 6.00);

-- Venda 20
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (20, 14, 5, 35.00),
    (20, 15, 10, 20.00),
    (20, 20, 3, 2.10);

-- Venda 21
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (21, 24, 1, 250.00),
    (21, 27, 1, 80.00);

-- Venda 22
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (22, 1, 20, 10.00),
    (22, 4, 5, 25.00),
    (22, 8, 10, 8.00),
    (22, 16, 4, 12.00),
    (22, 11, 2, 8.00),
    (22, 3, 1, 12.50);

-- Venda 23
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (23, 28, 2, 200.00);

-- Venda 24
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (24, 23, 1, 150.00),
    (24, 25, 2, 100.00);

-- Venda 25
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (25, 1, 50, 25.00);

-- Venda 26
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (26, 3, 2, 25.00);

-- Venda 27
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (27, 4, 10, 50.00);

-- Venda 28
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (28, 28, 1, 100.00);

-- Venda 29
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (29, 7, 2, 36.00),
    (29, 12, 2, 40.00),
    (29, 4, 4, 20.00),
    (29, 16, 2, 6.00),
    (29, 11, 2, 8.00);

-- Venda 30
INSERT INTO venda_produtos (venda_id, produto_id, quantidade, subtotal) VALUES
    (30, 2, 2, 30.00),
    (30, 3, 1, 12.50),
    (30, 1, 10, 5.00),
    (30, 4, 2, 10.00),
    (30, 1, 5, 2.50);
