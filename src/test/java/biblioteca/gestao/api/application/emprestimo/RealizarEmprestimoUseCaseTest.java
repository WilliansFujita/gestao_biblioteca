package biblioteca.gestao.api.application.emprestimo;

import biblioteca.gestao.api.application.emprestimo.dto.RealizarCadastroDTO;
import biblioteca.gestao.api.domain.DomainException;
import biblioteca.gestao.api.domain.emprestimo.Emprestimo;
import biblioteca.gestao.api.domain.livro.Livro;
import biblioteca.gestao.api.domain.usuario.Usuario;
import biblioteca.gestao.api.infra.repository.EmprestimoRepository;
import biblioteca.gestao.api.infra.repository.LivroRepository;
import biblioteca.gestao.api.infra.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class RealizarEmprestimoUseCaseTest {

    UsuarioRepository usuarioRepository;

    LivroRepository livroRepository;

    EmprestimoRepository emprestimoRepository;

    RealizarEmprestimoUseCase useCase;



    @Test
    public void dados_inputs_validos_deve_realizar_emprestimo(){
        livroRepository = Mockito.mock(LivroRepository.class);
        emprestimoRepository = Mockito.mock(EmprestimoRepository.class);
        usuarioRepository = Mockito.mock(UsuarioRepository.class);

        Livro livro = new Livro();
        livro.setAtivo(true);
        Mockito.when(livroRepository.getReferenceById(Mockito.any()))
                .thenReturn(livro);

        Mockito.when(emprestimoRepository.findByLivroId(Mockito.any()))
                .thenReturn(null);

        Usuario usuario = new Usuario();
        usuario.setAtivo(true);

        Mockito.when(usuarioRepository.getReferenceById(Mockito.any()))
                .thenReturn(usuario);

        RealizarCadastroDTO realizarCadastroDTO = new RealizarCadastroDTO(1L, 1L);
        useCase = new RealizarEmprestimoUseCase(emprestimoRepository, usuarioRepository, livroRepository);

        try {
            var output = useCase.execute(realizarCadastroDTO);
            Assertions.assertNotNull(output);
            Mockito.verify(emprestimoRepository, Mockito.times(1)).save(Mockito.any());
        } catch (DomainException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void dado_inputs_validos_deve_realizar_emprestimo_se_livro_emprestado(){
        livroRepository = Mockito.mock(LivroRepository.class);
        emprestimoRepository = Mockito.mock(EmprestimoRepository.class);
        usuarioRepository = Mockito.mock(UsuarioRepository.class);

        Livro livro = new Livro();
        livro.setAtivo(true);
        Mockito.when(livroRepository.getReferenceById(Mockito.any()))
                .thenReturn(livro);

        Emprestimo emprestimo = new Emprestimo();
        Mockito.when(emprestimoRepository.findByLivroId(Mockito.any()))
                .thenReturn(emprestimo);

        Usuario usuario = new Usuario();
        usuario.setAtivo(true);

        Mockito.when(usuarioRepository.getReferenceById(Mockito.any()))
                .thenReturn(usuario);

        RealizarCadastroDTO realizarCadastroDTO = new RealizarCadastroDTO(1L, 1L);
        useCase = new RealizarEmprestimoUseCase(emprestimoRepository, usuarioRepository, livroRepository);

        var thrown = Assertions.assertThrows(DomainException.class, (()->{
            useCase.execute(realizarCadastroDTO);
        }));

        Assertions.assertEquals("Livro está emprestado", thrown.getMessage());
    }
}
