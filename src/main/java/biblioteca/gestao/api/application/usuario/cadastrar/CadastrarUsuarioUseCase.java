package biblioteca.gestao.api.application.usuario.cadastrar;

import biblioteca.gestao.api.application.usuario.cadastrar.dto.CadastrarUsuarioUseCaseOutPut;
import biblioteca.gestao.api.application.usuario.cadastrar.dto.CadastroUsuarioUseCaseInput;
import biblioteca.gestao.api.domain.ValidationException;
import biblioteca.gestao.api.domain.usuario.Usuario;
import biblioteca.gestao.api.domain.usuario.ValidadorUsuario;
import biblioteca.gestao.api.infra.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioUseCase {

    UsuarioRepository usuarioRepository;

    public CadastrarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public CadastrarUsuarioUseCaseOutPut execute(CadastroUsuarioUseCaseInput input) throws ValidationException {
        Usuario usuario = Usuario.from(input);

        ValidadorUsuario validadorUsuario = new ValidadorUsuario();
        validadorUsuario.validar(usuario);

        if(!validadorUsuario.isValido()){
            throw new ValidationException(validadorUsuario.getErros());
        }

        usuarioRepository.save(usuario);
        return new CadastrarUsuarioUseCaseOutPut(usuario);
    }
}
