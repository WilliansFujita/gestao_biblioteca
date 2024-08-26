package biblioteca.gestao.api.application.usuario;

import biblioteca.gestao.api.domain.usuario.Telefone;
import biblioteca.gestao.api.domain.usuario.Usuario;

import java.util.Date;

public record ListarUsuarioDTO(
        Long id,
        String nome,
        String email,
        Telefone telefone,
        Date dataCadastro
) {

    public ListarUsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getDataCadastro());
    }
}
