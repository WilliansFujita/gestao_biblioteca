package biblioteca.gestao.api.application.livro.cadastrar.dto;

import biblioteca.gestao.api.domain.livro.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CadastrarLivroUseCaseInput(
        @NotBlank
        String titulo,
        @NotBlank
        String author,
        @NotBlank
        String isbn,

        Categoria categoria
) {
}
