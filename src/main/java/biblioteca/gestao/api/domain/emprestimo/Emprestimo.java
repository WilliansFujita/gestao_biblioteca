package biblioteca.gestao.api.domain.emprestimo;

import biblioteca.gestao.api.domain.livro.Livro;
import biblioteca.gestao.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "Emprestimo")
@Table(name = "emprestimos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emprestimos_id_seq")
    @SequenceGenerator(name = "emprestimos_id_seq", sequenceName = "emprestimos_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "data_devolucao")
    private Date dataDevolucao;

    @Column(name = "data_emprestimo")
    private Date dataEmprestimo;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    private boolean status;

    public static Emprestimo create(Livro livro, Usuario usuario, Date dataEmprestimo, Date dataDevolucao) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.dataEmprestimo = dataEmprestimo;
        emprestimo.dataDevolucao = dataDevolucao;
        emprestimo.status = true;
        emprestimo.livro = livro;
        emprestimo.usuario = usuario;
        return emprestimo;
    }
}
