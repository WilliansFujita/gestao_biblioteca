package biblioteca.gestao.api.infra.controller;

import biblioteca.gestao.api.application.livro.AtualizaLivroDTO;
import biblioteca.gestao.api.application.livro.CadastroLivroDTO;
import biblioteca.gestao.api.application.livro.DadosDetalhamentoLivroDTO;
import biblioteca.gestao.api.application.livro.ListarLivroDTO;
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

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroLivroDTO dados, UriComponentsBuilder uriBuilder){
        Livro livro = Livro.from(dados);
        repository.save(livro);

        var uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoLivroDTO(livro));

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
