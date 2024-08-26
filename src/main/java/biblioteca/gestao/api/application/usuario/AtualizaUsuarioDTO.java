package biblioteca.gestao.api.application.usuario;

public record AtualizaUsuarioDTO(
        String nome,
        String email,
        CadastroTelefoneDTO telefone
) {
}
