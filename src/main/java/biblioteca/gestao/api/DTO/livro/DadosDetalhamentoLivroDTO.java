package biblioteca.gestao.api.DTO.livro;

import biblioteca.gestao.api.model.Categoria;
import biblioteca.gestao.api.model.Livro;

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
