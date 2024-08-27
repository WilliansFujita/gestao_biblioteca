package biblioteca.gestao.api.application.usuario.cadastrar.dto;

import biblioteca.gestao.api.application.usuario.CadastroTelefoneDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroUsuarioUseCaseInput(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotNull
        CadastroTelefoneDTO telefone
) {
}
