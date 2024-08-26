package biblioteca.gestao.api.DTO.livro;

import biblioteca.gestao.api.model.Livro;

import java.util.Date;

public record ListarLivroDTO(
        Long id,
        String titulo,
        String author,
        String isbn,
        Date data_cadastro
) {
    public ListarLivroDTO(Livro livro) {
        this(
                livro.getId(),
                livro.getTitulo(),
                livro.getAuthor(),
                livro.getIsbn(),
                livro.getDataCadastro()
        );
    }
}
