package biblioteca.gestao.api.infra.controller;

import biblioteca.gestao.api.application.emprestimo.realizar_emprestimo.RealizarEmprestimoUseCase;
import biblioteca.gestao.api.application.emprestimo.realizar_emprestimo.dto.RealizarCadastroDTO;
import biblioteca.gestao.api.domain.DomainException;
import biblioteca.gestao.api.infra.repository.EmprestimoRepository;
import biblioteca.gestao.api.infra.repository.LivroRepository;
import biblioteca.gestao.api.infra.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("emprestimos")
public class EmprestimoController {

    @Autowired
    EmprestimoRepository emprestimoRepository;

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RealizarEmprestimoUseCase useCase;

    @PostMapping
    public ResponseEntity realizarEmprestimo(@RequestBody RealizarCadastroDTO dados) throws DomainException {
        var output = useCase.execute(dados);
        return ResponseEntity.ok(output);
    }
}
