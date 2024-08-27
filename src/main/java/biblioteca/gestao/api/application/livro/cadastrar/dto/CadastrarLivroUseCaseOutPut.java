package biblioteca.gestao.api.application.livro.cadastrar.dto;

import biblioteca.gestao.api.domain.livro.Categoria;
import biblioteca.gestao.api.domain.livro.Livro;

import java.util.Date;

public record CadastrarLivroUseCaseOutPut(
        Long id,
        String titulo,
        String author,
        String isbn,
        Categoria categoria,
        Date data_cadastro
) {

    public CadastrarLivroUseCaseOutPut(Livro livro) {
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
