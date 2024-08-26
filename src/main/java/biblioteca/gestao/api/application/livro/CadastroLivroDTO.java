package biblioteca.gestao.api.application.livro;

import biblioteca.gestao.api.domain.livro.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CadastroLivroDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String author,
        @NotBlank
        String isbn,

        Categoria categoria
) {

}
