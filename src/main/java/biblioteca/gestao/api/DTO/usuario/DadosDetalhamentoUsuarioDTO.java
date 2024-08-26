package biblioteca.gestao.api.DTO.usuario;

import biblioteca.gestao.api.model.Telefone;
import biblioteca.gestao.api.model.Usuario;

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
