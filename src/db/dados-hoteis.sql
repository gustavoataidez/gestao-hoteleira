
-- Inserção na tabela hotel
INSERT INTO hotel (hot_nome, hot_end, hot_bairro, hot_cid, hot_estado, hot_tel, hot_estrelas, hot_vlr_qua1, hot_vlr_qua2, hot_vlr_qua3, hot_obs, hot_site)
VALUES ('Maceió Atlantic Suítes', 'Avenida Álvaro Otacílio, 4065', 'Centro', 'Maceió', 'AL', '2121-5759', 4, 100.00, 80.00, 120.00, 'Com café', 'http://www.maceioatlantic.com.br/');

INSERT INTO hotel (hot_nome, hot_end, hot_bairro, hot_cid, hot_estado, hot_tel, hot_estrelas, hot_vlr_qua1, hot_vlr_qua2, hot_vlr_qua3, hot_obs, hot_site)
VALUES ('Ritz Lagoa da Anta Urban Resort', 'Av. Brigadeiro Eduardo Gomes, 546', 'Praia da Lagoa da Anta', 'Maceió', 'AL', '2121-4124', 3, 90.00, 70.00, 110.00, 'Com café', 'http://www.ritzlagoadaanta.com.br/');

-- Inserção na tabela quarto
INSERT INTO quarto (qua_hot, qua_nome, qua_camas, qua_valor_dia)
VALUES (1, 'Quarto Standard', 2, 100.00),
       (1, 'Quarto Luxo', 2, 120.00),
       (2, 'Quarto Duplo', 2, 90.00),
       (2, 'Quarto Família', 4, 150.00);
