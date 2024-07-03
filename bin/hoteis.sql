--CREATE

CREATE SEQUENCE IF NOT EXISTS hot_hot_id_seq START WITH 1;

CREATE TABLE IF NOT EXISTS cliente (
    cli_usuario CHAR(20) NOT NULL,
    cli_pnome   VARCHAR(50) NOT NULL,
    cli_unome   VARCHAR(50),
    cli_tel     VARCHAR(14)
);

ALTER TABLE cliente ADD CONSTRAINT pk_cliente PRIMARY KEY (cli_usuario);

CREATE TABLE IF NOT EXISTS hotel (
    hot_id       SERIAL PRIMARY KEY,
    hot_nome     VARCHAR(50) NOT NULL,
    hot_end      VARCHAR(100),
    hot_bairro   VARCHAR(50),
    hot_cid      VARCHAR(50),
    hot_estado   CHAR(2),
    hot_tel      VARCHAR(14),
    hot_estrelas INTEGER CHECK (hot_estrelas BETWEEN 1 AND 5),
    hot_vlr_qua1 INTEGER CHECK (hot_vlr_qua1 BETWEEN 0 AND 99999),
    hot_vlr_qua2 INTEGER CHECK (hot_vlr_qua2 BETWEEN 0 AND 99999),
    hot_vlr_qua3 INTEGER CHECK (hot_vlr_qua3 BETWEEN 0 AND 99999),
    hot_obs      VARCHAR(200),
    hot_site     VARCHAR(100),
    hot_status   CHAR(1) DEFAULT 'S' CHECK (hot_status IN ('N', 'S'))
);

CREATE TABLE IF NOT EXISTS quarto (
    qua_id        SERIAL PRIMARY KEY,
    qua_hot       INTEGER NOT NULL REFERENCES hotel (hot_id) ON DELETE CASCADE,
    qua_nome      VARCHAR(50),
    qua_camas     NUMERIC(2),
    qua_valor_dia NUMERIC(10)
);

CREATE TABLE IF NOT EXISTS reserva (
    res_id           SERIAL PRIMARY KEY,
    res_hot          INTEGER NOT NULL REFERENCES hotel (hot_id) ON DELETE CASCADE,
    res_qua          INTEGER NOT NULL REFERENCES quarto (qua_id) ON DELETE CASCADE,
    res_cli          CHAR(20) NOT NULL REFERENCES cliente (cli_usuario) ON DELETE CASCADE,
    res_data_entrada DATE NOT NULL,
    res_data_saida   DATE NOT NULL,
    res_status       CHAR(1) DEFAULT 'S'
);

ALTER TABLE reserva ADD CONSTRAINT unique_reserva_hot_qua_data UNIQUE (res_hot, res_qua,res_data_entrada);

CREATE OR REPLACE FUNCTION hot_hot_id_trg()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.hot_id IS NULL THEN
        NEW.hot_id := nextval('hot_hot_id_seq');
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER hot_hot_id_trg BEFORE INSERT ON hotel
FOR EACH ROW EXECUTE FUNCTION hot_hot_id_trg();

CREATE SEQUENCE qua_id_seq START WITH 1;

CREATE OR REPLACE FUNCTION qua_id_trg()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.qua_id IS NULL THEN
        NEW.qua_id := nextval('qua_id_seq');
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER qua_id_trg BEFORE INSERT ON quarto
FOR EACH ROW EXECUTE FUNCTION qua_id_trg();

CREATE SEQUENCE res_res_id_seq START WITH 1;

CREATE OR REPLACE FUNCTION res_res_id_trg()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.res_id IS NULL THEN
        NEW.res_id := nextval('res_res_id_seq');
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER res_res_id_trg BEFORE INSERT ON reserva
FOR EACH ROW EXECUTE FUNCTION res_res_id_trg();





--INSERT

-- Inserção na tabela cliente
INSERT INTO cliente (cli_usuario, cli_pnome, cli_unome, cli_tel)
VALUES ('user1', 'Primeiro', 'Cliente', '123456789');

INSERT INTO cliente (cli_usuario, cli_pnome, cli_unome, cli_tel)
VALUES ('user2', 'Segundo', 'Cliente', '987654321');

-- Inserção na tabela hotel
INSERT INTO hotel (hot_nome, hot_end, hot_bairro, hot_cid, hot_estado, hot_tel, hot_estrelas, hot_vlr_qua1, hot_vlr_qua2, hot_vlr_qua3, hot_obs, hot_site)
VALUES ('Hotel 1', 'Rua A, 123', 'Centro', 'Cidade X', 'XX', '987654321', 4, 100.00, 80.00, 120.00, 'Observações sobre o hotel 1', 'www.hotel1.com');

INSERT INTO hotel (hot_nome, hot_end, hot_bairro, hot_cid, hot_estado, hot_tel, hot_estrelas, hot_vlr_qua1, hot_vlr_qua2, hot_vlr_qua3, hot_obs, hot_site)
VALUES ('Hotel 2', 'Avenida B, 456', 'Bairro Y', 'Cidade Y', 'YY', '123456789', 3, 90.00, 70.00, 110.00, 'Observações sobre o hotel 2', 'www.hotel2.com');

-- Inserção na tabela quarto
INSERT INTO quarto (qua_hot, qua_nome, qua_camas, qua_valor_dia)
VALUES (1, 'Quarto Standard', 2, 100.00),
       (1, 'Quarto Luxo', 2, 120.00),
       (2, 'Quarto Duplo', 2, 90.00),
       (2, 'Quarto Família', 4, 150.00);

-- Inserção na tabela reserva
INSERT INTO reserva (res_hot, res_qua, res_cli, res_data_entrada, res_data_saida)
VALUES (1, 1, 'user1', '2024-05-10', '2024-05-15'),
       (2, 3, 'user2', '2024-06-20', '2024-06-25');
