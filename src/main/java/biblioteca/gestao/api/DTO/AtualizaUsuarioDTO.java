package biblioteca.gestao.api.DTO;

import jakarta.validation.constraints.NotNull;

public record AtualizaUsuarioDTO(
        String nome,
        String email,
        CadastroTelefoneDTO telefone
) {
}
