-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS consultas_db;
USE consultas_db;

-- Tabela de Pacientes
CREATE TABLE IF NOT EXISTS paciente (
    id INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL
);

-- Tabela de Médicos
CREATE TABLE IF NOT EXISTS medico (
    id INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
    nome VARCHAR(100) NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    crm VARCHAR(20) NOT NULL UNIQUE
);

-- Tabela de Consultas
DROP TABLE IF EXISTS consulta;

DROP TABLE IF EXISTS consulta;

CREATE TABLE consulta (
    id INT PRIMARY KEY AUTO_INCREMENT,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    observacao TEXT,
    paciente_id INT NOT NULL,
    medico_id INT NOT NULL,
    CONSTRAINT fk_paciente FOREIGN KEY (paciente_id) REFERENCES paciente(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_medico FOREIGN KEY (medico_id) REFERENCES medico(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO paciente (nome, cpf, telefone) VALUES ('Maicon', '123.456.789-00', '11999999999');
INSERT INTO medico (nome, especialidade, crm) VALUES ('Dra. Grey', 'Clínica Geral', 'CRM1234');

SHOW COLUMNS FROM consulta;

