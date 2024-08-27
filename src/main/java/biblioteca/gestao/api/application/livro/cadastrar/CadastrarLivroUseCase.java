package biblioteca.gestao.api.application.livro.cadastrar;

import biblioteca.gestao.api.application.livro.cadastrar.dto.CadastrarLivroUseCaseInput;
import biblioteca.gestao.api.application.livro.cadastrar.dto.CadastrarLivroUseCaseOutPut;
import biblioteca.gestao.api.domain.ValidationException;
import biblioteca.gestao.api.domain.livro.Livro;
import biblioteca.gestao.api.domain.livro.ValidadorLivro;
import biblioteca.gestao.api.infra.repository.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class CadastrarLivroUseCase {

    private final LivroRepository livroRepository;

    public CadastrarLivroUseCase(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public CadastrarLivroUseCaseOutPut execute(CadastrarLivroUseCaseInput input) throws ValidationException {
        Livro livro = Livro.from(input);

        ValidadorLivro validadorLivro = new ValidadorLivro();
        validadorLivro.validar(livro);

        if(!validadorLivro.isValido()){
            throw new ValidationException(validadorLivro.getErros());
        }

        livroRepository.save(livro);

        return new CadastrarLivroUseCaseOutPut(livro);

    }
}
