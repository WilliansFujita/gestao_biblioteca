package biblioteca.gestao.api.DTO.usuario;

import biblioteca.gestao.api.model.Telefone;
import biblioteca.gestao.api.model.Usuario;

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
