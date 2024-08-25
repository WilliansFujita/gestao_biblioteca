package biblioteca.gestao.api.DTO.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroUsuarioDTO(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotNull
        CadastroTelefoneDTO telefone
) {
}
