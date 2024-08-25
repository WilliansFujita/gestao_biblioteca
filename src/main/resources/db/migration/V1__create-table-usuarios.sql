CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    data_cadastro DATE DEFAULT CURRENT_DATE,
    telefone VARCHAR(20) NOT NULL,
    ativo BOOLEAN NOT NULL
);
