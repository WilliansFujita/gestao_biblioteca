create table usuarios(
    id varchar(36) not null,
    nome varchar(100) not null,
    email varchar(100) not null,
    data-cadastro DATE not null,
    numero-telefone varchar(20) not null,

    primary key(id)
);