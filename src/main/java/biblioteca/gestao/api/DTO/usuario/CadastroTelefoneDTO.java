package biblioteca.gestao.api.DTO.usuario;

import jakarta.validation.constraints.NotBlank;

public record CadastroTelefoneDTO(
        @NotBlank
        String numero
) {
}
