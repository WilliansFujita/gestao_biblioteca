package biblioteca.gestao.api.application.livro;

import biblioteca.gestao.api.domain.livro.Categoria;

public record AtualizaLivroDTO(
        String titulo,
        String author,
        String isbn,
        Categoria categoria
) {
}
