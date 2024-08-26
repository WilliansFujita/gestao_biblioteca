package biblioteca.gestao.api.domain.usuario;

import biblioteca.gestao.api.application.usuario.CadastroTelefoneDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Telefone {
    @Column(name = "telefone")
    private String numero;

    public static Telefone from(CadastroTelefoneDTO telefone) {
        Telefone tel = new Telefone();
        tel.numero = telefone.numero();
        return tel;
    }

    public void atualizarTelefone(CadastroTelefoneDTO telefone) {
        if(telefone.numero() != null)
            this.numero = telefone.numero();
    }
}
