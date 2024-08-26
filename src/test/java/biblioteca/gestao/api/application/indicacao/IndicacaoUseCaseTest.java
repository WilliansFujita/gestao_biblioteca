package biblioteca.gestao.api.application.indicacao;

import biblioteca.gestao.api.application.indicacoes.IndicacaoLivrosUseCase;
import biblioteca.gestao.api.application.indicacoes.dto.IndicarLivrosUseCaseInputDTO;
import biblioteca.gestao.api.domain.livro.Livro;
import biblioteca.gestao.api.domain.usuario.Usuario;
import biblioteca.gestao.api.infra.repository.LivroRepository;
import biblioteca.gestao.api.infra.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IndicacaoUseCaseTest {

    @Test
    public void dado_usuario_valido_deve_indicar_lista_de_livros(){

        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
        LivroRepository livroRepository = mock(LivroRepository.class);

        Usuario usuario = new Usuario();
        usuario.setAtivo(true);
        when(usuarioRepository.getReferenceById(any()))
                .thenReturn(usuario);

        Livro livro = new Livro();
        livro.setTitulo("livro1");

        Livro livro2 = new Livro();
        livro2.setTitulo("livro2");

        List<Livro> livros = List.of(livro, livro2);

        when(livroRepository.buscarLivrosNaoEmprestados(any())).thenReturn(livros);

        IndicacaoLivrosUseCase useCase = new IndicacaoLivrosUseCase(livroRepository, usuarioRepository);


        var output = useCase.execute(new IndicarLivrosUseCaseInputDTO(1L));

        Assertions.assertEquals(output.livros().size(), 2);
    }
}
