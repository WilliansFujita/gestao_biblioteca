package biblioteca.gestao.api.application.indicacoes;

import biblioteca.gestao.api.application.indicacoes.dto.IndicarLivrosUseCaseInputDTO;
import biblioteca.gestao.api.application.indicacoes.dto.IndicarLivrosUseCaseOutputDTO;
import biblioteca.gestao.api.domain.livro.Livro;
import biblioteca.gestao.api.domain.usuario.Usuario;
import biblioteca.gestao.api.infra.repository.LivroRepository;
import biblioteca.gestao.api.infra.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicacaoLivrosUseCase {

    LivroRepository livroRepository;
    UsuarioRepository usuarioRepository;

    public IndicacaoLivrosUseCase(LivroRepository livroRepository, UsuarioRepository usuarioRepository) {
        this.livroRepository = livroRepository;
        this.usuarioRepository= usuarioRepository;
    }

    public IndicarLivrosUseCaseOutputDTO execute(IndicarLivrosUseCaseInputDTO dados){
        Usuario usuario = usuarioRepository.getReferenceById(dados.id_usuario());
        if(!usuario.isAtivo()){
            throw new EntityNotFoundException();
        }

        if(usuario==null){
            throw new EntityNotFoundException();
        }

        List<Livro> livros = livroRepository.buscarLivrosNaoEmprestados(usuario.getId());
        List<String> livrosDescricao = livros.stream().map(livro -> livro.getTitulo()).toList();

        return new IndicarLivrosUseCaseOutputDTO(livrosDescricao);
    }
}
