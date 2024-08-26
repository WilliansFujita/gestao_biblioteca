package biblioteca.gestao.api.application.livro;

import biblioteca.gestao.api.domain.livro.Categoria;
import biblioteca.gestao.api.domain.livro.Livro;

import java.util.Date;

public record DadosDetalhamentoLivroDTO(
    Long id,
    String titulo,
    String author,
    String isbn,
    Categoria categoria,
    Date data_cadastro
) {

    public DadosDetalhamentoLivroDTO(Livro livro) {
        this(
                livro.getId(),
                livro.getTitulo(),
                livro.getAuthor(),
                livro.getIsbn(),
                livro.getCategoria(),
                livro.getDataCadastro()
        );
    }
}
