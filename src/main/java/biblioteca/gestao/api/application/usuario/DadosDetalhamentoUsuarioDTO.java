package biblioteca.gestao.api.application.usuario;

import biblioteca.gestao.api.domain.usuario.Usuario;

import java.util.Date;

public record DadosDetalhamentoUsuarioDTO(
        Long id,
        String nome,
        String email,
        CadastroTelefoneDTO telefone,
        Date data_cadastro
) {
    public DadosDetalhamentoUsuarioDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                new CadastroTelefoneDTO(usuario.getTelefone().getNumero()),
                usuario.getDataCadastro()
        );
    }
}
