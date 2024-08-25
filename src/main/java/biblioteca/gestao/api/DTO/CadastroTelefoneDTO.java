package biblioteca.gestao.api.DTO;

import jakarta.validation.constraints.NotBlank;

public record CadastroTelefoneDTO(
        @NotBlank
        String numero
) {
}
