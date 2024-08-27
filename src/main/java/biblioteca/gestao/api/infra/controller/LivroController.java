package biblioteca.gestao.api.infra.controller;

import biblioteca.gestao.api.application.livro.AtualizaLivroDTO;
import biblioteca.gestao.api.application.livro.DadosDetalhamentoLivroDTO;
import biblioteca.gestao.api.application.livro.ListarLivroDTO;
import biblioteca.gestao.api.application.livro.cadastrar.CadastrarLivroUseCase;
import biblioteca.gestao.api.application.livro.cadastrar.dto.CadastrarLivroUseCaseInput;
import biblioteca.gestao.api.application.livro.cadastrar.dto.CadastrarLivroUseCaseOutPut;
import biblioteca.gestao.api.domain.ValidationException;
import biblioteca.gestao.api.domain.livro.Livro;
import biblioteca.gestao.api.infra.repository.LivroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("livros")
public class LivroController {

    @Autowired
    LivroRepository repository;

    @Autowired
    CadastrarLivroUseCase cadastrarLivroUseCase;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastrarLivroUseCaseInput dados, UriComponentsBuilder uriBuilder) throws ValidationException {

        CadastrarLivroUseCaseOutPut output = cadastrarLivroUseCase.execute(dados);

        var uri = uriBuilder.path("/livros/{id}").buildAndExpand(output.id()).toUri();

        return ResponseEntity.created(uri).body(output);

    }


    @GetMapping
    public ResponseEntity<Page<ListarLivroDTO>> listar(Pageable paginacao){
        var page =  repository.findAllByAtivoTrue(paginacao).map(ListarLivroDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        Livro livro = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoLivroDTO(livro));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizaLivroDTO livroDTO, @PathVariable Long id){
        Livro livro = repository.getReferenceById(id);
        livro.atualizar(livroDTO);

        return ResponseEntity.ok(new DadosDetalhamentoLivroDTO(livro));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Livro livro = repository.getReferenceById(id);
        livro.excluir();
        return ResponseEntity.noContent().build();
    }
}
