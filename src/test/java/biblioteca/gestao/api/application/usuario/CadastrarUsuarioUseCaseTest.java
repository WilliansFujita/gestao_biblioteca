package biblioteca.gestao.api.application.usuario;

import biblioteca.gestao.api.application.usuario.cadastrar.CadastrarUsuarioUseCase;
import biblioteca.gestao.api.application.usuario.cadastrar.dto.CadastrarUsuarioUseCaseOutPut;
import biblioteca.gestao.api.application.usuario.cadastrar.dto.CadastroUsuarioUseCaseInput;
import biblioteca.gestao.api.domain.ValidationException;
import biblioteca.gestao.api.domain.usuario.Telefone;
import biblioteca.gestao.api.infra.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CadastrarUsuarioUseCaseTest {

    @Test
    public void dado_usuario_valido_deve_persistir_usuario() throws ValidationException {
        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
        when(usuarioRepository.save(any()))
                .thenAnswer(returnsFirstArg());

        var expectedNome = "Teste";
        var expectedEmail = "teste@teste.com";

        CadastroUsuarioUseCaseInput input = new CadastroUsuarioUseCaseInput(expectedNome, expectedEmail, new CadastroTelefoneDTO("123"));
        CadastrarUsuarioUseCase useCase = new CadastrarUsuarioUseCase(usuarioRepository);

        CadastrarUsuarioUseCaseOutPut output = useCase.execute(input);
        Assertions.assertNotNull(output);
        Assertions.assertEquals(output.nome(), expectedNome);
        Assertions.assertEquals(output.email(), expectedEmail);
        verify(usuarioRepository, times(1)).save(any());

    }
}
