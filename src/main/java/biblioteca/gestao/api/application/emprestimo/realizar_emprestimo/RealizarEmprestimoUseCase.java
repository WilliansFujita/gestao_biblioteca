package biblioteca.gestao.api.application.emprestimo.realizar_emprestimo;

import biblioteca.gestao.api.application.emprestimo.realizar_emprestimo.dto.DetalharEmprestimoDTO;
import biblioteca.gestao.api.application.emprestimo.realizar_emprestimo.dto.RealizarCadastroDTO;
import biblioteca.gestao.api.domain.DomainException;
import biblioteca.gestao.api.domain.emprestimo.Emprestimo;
import biblioteca.gestao.api.domain.livro.Livro;
import biblioteca.gestao.api.domain.usuario.Usuario;
import biblioteca.gestao.api.infra.repository.EmprestimoRepository;
import biblioteca.gestao.api.infra.repository.LivroRepository;
import biblioteca.gestao.api.infra.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RealizarEmprestimoUseCase {

    EmprestimoRepository emprestimoRepository;

    UsuarioRepository usuarioRepository;

    LivroRepository livroRepository;

    public RealizarEmprestimoUseCase(EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository, LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    public DetalharEmprestimoDTO execute(RealizarCadastroDTO dados) throws DomainException {

        Livro livro = livroRepository.getReferenceById(dados.id_livro());

        if(livro==null) {
            throw new EntityNotFoundException();
        }

        if(!livro.isAtivo()){
            throw new EntityNotFoundException();
        }

        Usuario usuario = usuarioRepository.getReferenceById(dados.id_usuario());
        if(!usuario.isAtivo()){
            throw new EntityNotFoundException();
        }

        if(usuario==null){
            throw new EntityNotFoundException();
        }

        Emprestimo emprestimo = emprestimoRepository.findByLivroId(dados.id_livro());
        if(emprestimo!=null){
            throw new DomainException("Livro está emprestado");
        }

        Emprestimo emprestimo_novo = Emprestimo.create(livro, usuario, new Date(), new Date());
        emprestimoRepository.save(emprestimo_novo);

        return new DetalharEmprestimoDTO(emprestimo_novo);
    }
}
