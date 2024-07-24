-- Sequência para identificação de hotel
CREATE SEQUENCE hot_hot_id_seq START WITH 1;

-- Tabela de pessoa
CREATE TABLE pessoa (
    usuario CHAR(20) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    telefone VARCHAR(14),
    CONSTRAINT pk_pessoa PRIMARY KEY (usuario)
);

-- Tabela de hotel
CREATE TABLE hotel (
    hot_id SERIAL PRIMARY KEY,
    hot_nome VARCHAR(50) NOT NULL,
    hot_end VARCHAR(100),
    hot_bairro VARCHAR(50),
    hot_cid VARCHAR(50),
    hot_estado CHAR(2),
    hot_tel VARCHAR(14),
    hot_estrelas INTEGER CHECK (hot_estrelas BETWEEN 1 AND 5),
    hot_vlr_qua1 INTEGER CHECK (hot_vlr_qua1 BETWEEN 0 AND 99999),
    hot_vlr_qua2 INTEGER CHECK (hot_vlr_qua2 BETWEEN 0 AND 99999),
    hot_vlr_qua3 INTEGER CHECK (hot_vlr_qua3 BETWEEN 0 AND 99999),
    hot_obs VARCHAR(200),
    hot_site VARCHAR(100),
    hot_status CHAR(1) DEFAULT 'S' CHECK (hot_status IN ('N', 'S'))
);

-- Tabela de quarto
CREATE TABLE quarto (
    qua_id SERIAL PRIMARY KEY,
    qua_hot INTEGER NOT NULL REFERENCES hotel (hot_id) ON DELETE CASCADE,
    qua_nome VARCHAR(50),
    qua_tipo VARCHAR(50),
    qua_jacuzzi VARCHAR(50),
    qua_sala_de_estar VARCHAR(50),
    qua_piscina_privativa VARCHAR(50),
    qua_camas NUMERIC(2),
    qua_valor_dia NUMERIC(10)
);

-- Tabela de reserva
CREATE TABLE reserva (
    res_id SERIAL PRIMARY KEY,
    res_hot INTEGER NOT NULL REFERENCES hotel (hot_id) ON DELETE CASCADE,
    res_qua INTEGER NOT NULL REFERENCES quarto (qua_id) ON DELETE CASCADE,
    res_pessoa CHAR(20) NOT NULL REFERENCES pessoa (usuario) ON DELETE CASCADE,
    res_data_entrada DATE NOT NULL,
    res_data_saida DATE NOT NULL,
    res_status CHAR(1) DEFAULT 'S'
);

ALTER TABLE reserva ADD CONSTRAINT unique_reserva_hot_qua_data UNIQUE (res_hot, res_qua, res_data_entrada);

-- Tabela de pessoa_fisica
CREATE TABLE pessoa_fisica (
    pf_usuario CHAR(20) NOT NULL PRIMARY KEY,
    pf_nome VARCHAR(50),
    pf_cpf INTEGER,
    pf_sexo CHAR(1),
    CONSTRAINT fk_pf_usuario FOREIGN KEY (pf_usuario) REFERENCES pessoa (usuario)
);

-- Tabela de pessoa_juridica
CREATE TABLE pessoa_juridica (
    pj_usuario CHAR(20) NOT NULL PRIMARY KEY,
    pj_nome VARCHAR(50),
    pj_cnpj INTEGER,
    pj_razao VARCHAR(100),
    CONSTRAINT fk_pj_usuario FOREIGN KEY (pj_usuario) REFERENCES pessoa (usuario)
);

-- Tabela de funcionario
CREATE TABLE funcionario (
    fun_usuario CHAR(20) NOT NULL PRIMARY KEY,
    fun_nome VARCHAR(50),
    fun_cpf INTEGER,
    fun_sexo CHAR(1),
    fun_cargo VARCHAR(50),
    fun_salario integer,
    CONSTRAINT fk_fun_usuario FOREIGN KEY (fun_usuario) REFERENCES pessoa (usuario)
);

-- Função e gatilho para a sequência de hotel
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

-- Sequência para identificação de quarto
CREATE SEQUENCE qua_id_seq START WITH 1;

-- Função e gatilho para a sequência de quarto
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

-- Sequência para identificação de reserva
CREATE SEQUENCE res_res_id_seq START WITH 1;

-- Função e gatilho para a sequência de reserva
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
