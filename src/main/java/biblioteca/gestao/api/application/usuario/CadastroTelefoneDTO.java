package biblioteca.gestao.api.application.usuario;

import jakarta.validation.constraints.NotBlank;

public record CadastroTelefoneDTO(
        @NotBlank
        String numero
) {
}
