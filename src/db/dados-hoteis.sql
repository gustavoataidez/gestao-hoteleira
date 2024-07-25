
-- Inserção na tabela cliente
INSERT INTO pessoa (usuario, nome,telefone)
VALUES ('fer', 'Fernando', '123456789');

INSERT INTO pessoa (usuario, nome,telefone)
VALUES ('gustavo', 'Gustavo', '987654321');

INSERT INTO pessoa (usuario, nome,telefone)
VALUES ('usuario1', 'Ana Silva', '123456789');

INSERT INTO pessoa (usuario, nome,telefone)
VALUES ('usuario2', 'Bruno Souza', '987654321');

-- Inserção na tabela funcionario
INSERT INTO funcionario (fun_usuario, fun_nome, fun_cpf, fun_sexo, fun_cargo, fun_salario) VALUES
('usuario2', 'Bruno Souza', 234568901, 'M', 'Gerente', 500);

INSERT INTO funcionario (fun_usuario, fun_nome, fun_cpf, fun_sexo, fun_cargo, fun_salario) VALUES
('usuario1', 'Ana Silva', 1234567890, 'F', 'Recepcionista', 200),;


-- Inserção na tabela hotel
INSERT INTO hotel (hot_nome, hot_end, hot_bairro, hot_cid, hot_estado, hot_tel, hot_estrelas, hot_vlr_qua1, hot_vlr_qua2, hot_vlr_qua3, hot_obs, hot_site)
VALUES ('Maceió Atlantic Suítes', 'Avenida Álvaro Otacílio, 4065', 'Centro', 'Maceió', 'AL', '2121-5759', 5, 100.00, 80.00, 120.00, 'Com café', 'http://www.maceioatlantic.com.br/');

INSERT INTO hotel (hot_nome, hot_end, hot_bairro, hot_cid, hot_estado, hot_tel, hot_estrelas, hot_vlr_qua1, hot_vlr_qua2, hot_vlr_qua3, hot_obs, hot_site)
VALUES ('Ritz Lagoa da Anta Urban Resort', 'Av. Brigadeiro Eduardo Gomes, 546', 'Praia da Lagoa da Anta', 'Maceió', 'AL', '2121-4124', 5, 90.00, 70.00, 110.00, 'Com café', 'http://www.ritzlagoadaanta.com.br/');

INSERT INTO hotel (hot_nome, hot_end, hot_bairro, hot_cid, hot_estado, hot_tel, hot_estrelas, hot_vlr_qua1, hot_vlr_qua2, hot_vlr_qua3, hot_obs, hot_site)
VALUES ('Matsubara Hotel', 'Av. Brigadeiro Eduardo Gomes', 'Lagoa da Anta', 'Maceió', 'AL', '3214-3005', 4, 90.00, 70.00, 110.00, 'Com café', 'http://www.matsubarahotel.com.br/');

INSERT INTO hotel (hot_nome, hot_end, hot_bairro, hot_cid, hot_estado, hot_tel, hot_estrelas, hot_vlr_qua1, hot_vlr_qua2, hot_vlr_qua3, hot_obs, hot_site)
VALUES ('Hotel Ponta Verde', 'Av. Álvaro Otacílio, 2933', 'Ponta Verde', 'Maceió', 'AL', '2121-0901', 4, 80.00, 70.00, 110.00, 'Com café', 'http://www.hotelpontaverde.com.br/');

INSERT INTO hotel (hot_nome, hot_end, hot_bairro, hot_cid, hot_estado, hot_tel, hot_estrelas, hot_vlr_qua1, hot_vlr_qua2, hot_vlr_qua3, hot_obs, hot_site)
VALUES ('Hotel Enseada', 'Av. Dr. Antônio Gouveia, 171', 'Pajuçara', 'Maceió', 'AL', '3214-3700', 3, 50.00, 60.00, 100.00, 'Sem café', 'http://www.hotelenseada.com.br/');

-- Inserção na tabela quarto para Maceió Atlantic Suítes (id 1)
INSERT INTO quarto (qua_hot, qua_nome, qua_camas, qua_valor_dia) VALUES
(1, 'Standard Simples', 2, 100.00),
(1, 'Standard Duplo', 2, 100.00),
(1, 'Luxo Simples', 2, 120.00),
(1, 'Luxo Duplo', 2, 120.00),
(1, 'Quarto Família', 4, 150.00);

-- Inserção na tabela quarto para Ritz Lagoa da Anta Urban Resort (id 2)
INSERT INTO quarto (qua_hot, qua_nome, qua_camas, qua_valor_dia) VALUES
(2, 'Standard Simples', 2, 90.00),
(2, 'Standard Duplo', 2, 90.00),
(2, 'Luxo Simples', 2, 110.00),
(2, 'Luxo Duplo', 2, 110.00),
(2, 'Quarto Família', 4, 150.00);

-- Inserção na tabela quarto para Matsubara Hotel (id 3)
INSERT INTO quarto (qua_hot, qua_nome, qua_camas, qua_valor_dia) VALUES
(3, 'Standard Simples', 2, 90.00),
(3, 'Standard Duplo', 2, 90.00),
(3, 'Luxo Simples', 2, 110.00),
(3, 'Luxo Duplo', 2, 110.00),
(3, 'Quarto Família', 4, 150.00);

-- Inserção na tabela quarto para Hotel Ponta Verde (id 4)
INSERT INTO quarto (qua_hot, qua_nome, qua_camas, qua_valor_dia) VALUES
(4, 'Standard Simples', 2, 80.00),
(4, 'Standard Duplo', 2, 80.00),
(4, 'Luxo Simples', 2, 110.00),
(4, 'Luxo Duplo', 2, 110.00),
(4, 'Quarto Família', 4, 150.00);

-- Inserção na tabela quarto para Hotel Enseada (id 5)
INSERT INTO quarto (qua_hot, qua_nome, qua_camas, qua_valor_dia) VALUES
(5, 'Standard Simples', 2, 50.00),
(5, 'Standard Duplo', 2, 50.00),
(5, 'Luxo Simples', 2, 60.00),
(5, 'Luxo Duplo', 2, 60.00),
(5, 'Quarto Família', 4, 100.00);
