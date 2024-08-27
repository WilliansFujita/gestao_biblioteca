package biblioteca.gestao.api.domain.livro;

import biblioteca.gestao.api.application.livro.AtualizaLivroDTO;
import biblioteca.gestao.api.application.livro.CadastroLivroDTO;
import biblioteca.gestao.api.application.livro.cadastrar.dto.CadastrarLivroUseCaseInput;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "Livro")
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livros_id_seq")
    @SequenceGenerator(name = "livros_id_seq", sequenceName = "livros_id_seq", allocationSize = 1)
    private Long id;
    private String titulo;
    private String author;
    private String isbn;
    private Date dataCadastro;
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public static Livro from(@Valid CadastrarLivroUseCaseInput dados) {
        var livro = new Livro();
        livro.author = dados.author();
        livro.dataCadastro = new Date();
        livro.titulo = dados.titulo();
        livro.isbn = dados.isbn();
        livro.categoria = dados.categoria();
        livro.ativo = true;
        return livro;
    }

    public void atualizar(@Valid AtualizaLivroDTO livroDTO) {
        if(livroDTO.author() != null)
            this.author = livroDTO.author();

        if(livroDTO.titulo() != null)
            this.titulo = livroDTO.titulo();

        if(livroDTO.isbn() != null)
            this.isbn = livroDTO.isbn();

        if(livroDTO.categoria() != null)
            this.categoria = livroDTO.categoria();
    }

    public void excluir() {
        this.ativo = false;
    }
}
