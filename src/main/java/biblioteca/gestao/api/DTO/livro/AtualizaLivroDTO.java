package biblioteca.gestao.api.DTO.livro;

import biblioteca.gestao.api.model.Categoria;
import jakarta.validation.constraints.NotBlank;

public record AtualizaLivroDTO(
        String titulo,
        String author,
        String isbn,
        Categoria categoria
) {
}
