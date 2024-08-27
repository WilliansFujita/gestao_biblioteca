package biblioteca.gestao.api.domain.usuario;

import biblioteca.gestao.api.application.usuario.AtualizaUsuarioDTO;
import biblioteca.gestao.api.application.usuario.cadastrar.dto.CadastroUsuarioUseCaseInput;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_id_seq")
    @SequenceGenerator(name = "usuarios_id_seq", sequenceName = "usuarios_id_seq", allocationSize = 1)
    private Long id;
    private String nome;
    private String email;
    private Date dataCadastro;
    private boolean ativo;

    @Embedded
    private Telefone telefone;

    public static Usuario from(CadastroUsuarioUseCaseInput usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.nome = usuarioDTO.nome();
        usuario.dataCadastro = new Date();
        usuario.email = usuarioDTO.email();
        usuario.telefone = Telefone.from(usuarioDTO.telefone());
        usuario.ativo = true;
        return usuario;
    }

    public void atualizar(AtualizaUsuarioDTO usuarioDTO) {
        if(usuarioDTO.nome()!=null)
            this.nome = usuarioDTO.nome();

        if(usuarioDTO.email() != null)
            this.email = usuarioDTO.email();

        if(usuarioDTO.telefone()!=null)
            this.telefone.atualizarTelefone(usuarioDTO.telefone());
    }

    public void excluir() {
        this.ativo = false;
    }
}
