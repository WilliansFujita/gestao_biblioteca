package biblioteca.gestao.api.application.usuario.cadastrar.dto;

import biblioteca.gestao.api.application.usuario.CadastroTelefoneDTO;
import biblioteca.gestao.api.domain.usuario.Usuario;

import java.util.Date;

public record CadastrarUsuarioUseCaseOutPut(
        Long id,
        String nome,
        String email,
        CadastroTelefoneDTO telefone,
        Date data_cadastro
) {
    public CadastrarUsuarioUseCaseOutPut(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                new CadastroTelefoneDTO(usuario.getTelefone().getNumero()),
                usuario.getDataCadastro()
        );
    }
}
