package biblioteca.gestao.api.DTO;

import biblioteca.gestao.api.model.Telefone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

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
