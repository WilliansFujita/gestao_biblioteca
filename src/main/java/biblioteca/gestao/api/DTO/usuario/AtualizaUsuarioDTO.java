package biblioteca.gestao.api.DTO.usuario;

public record AtualizaUsuarioDTO(
        String nome,
        String email,
        CadastroTelefoneDTO telefone
) {
}
