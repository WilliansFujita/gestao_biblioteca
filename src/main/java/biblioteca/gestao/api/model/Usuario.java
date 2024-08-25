package biblioteca.gestao.api.model;

import biblioteca.gestao.api.DTO.CadastroUsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
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

    @Embedded
    private Telefone telefone;

    public static Usuario from(CadastroUsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.nome = usuarioDTO.nome();
        usuario.dataCadastro = new Date();
        usuario.email = usuarioDTO.email();
        usuario.telefone = Telefone.from(usuarioDTO.telefone());
        return usuario;
    }
}
