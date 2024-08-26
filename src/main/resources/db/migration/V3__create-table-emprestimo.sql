CREATE TABLE emprestimos (
    id SERIAL PRIMARY KEY,
    usuario_id INT not null,
    livro_id INT not null,
    data_devolucao DATE not null,
    data_emprestimo DATE not null,
    status BOOLEAN NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (livro_id) REFERENCES livros(id)
);