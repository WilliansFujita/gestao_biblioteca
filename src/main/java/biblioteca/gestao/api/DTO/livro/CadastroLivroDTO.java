package biblioteca.gestao.api.DTO.livro;

import biblioteca.gestao.api.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

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
