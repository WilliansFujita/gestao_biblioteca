package biblioteca.gestao.api.infra.controller;

import biblioteca.gestao.api.application.indicacoes.IndicacaoLivrosUseCase;
import biblioteca.gestao.api.application.indicacoes.dto.IndicarLivrosUseCaseInputDTO;
import biblioteca.gestao.api.infra.repository.LivroRepository;
import biblioteca.gestao.api.infra.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("indicacoes")
public class IndicacaoController {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    IndicacaoLivrosUseCase indicacaoUseCase;

    @PostMapping("/indica")
    public ResponseEntity indicarLivros(@RequestBody IndicarLivrosUseCaseInputDTO dto){
        var output = indicacaoUseCase.execute(dto);
        return ResponseEntity.ok(output);
    }

}
