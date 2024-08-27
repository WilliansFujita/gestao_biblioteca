package biblioteca.gestao.api.infra.controller;

import biblioteca.gestao.api.application.usuario.AtualizaUsuarioDTO;
import biblioteca.gestao.api.application.usuario.DadosDetalhamentoUsuarioDTO;
import biblioteca.gestao.api.application.usuario.ListarUsuarioDTO;
import biblioteca.gestao.api.application.usuario.cadastrar.CadastrarUsuarioUseCase;
import biblioteca.gestao.api.application.usuario.cadastrar.dto.CadastrarUsuarioUseCaseOutPut;
import biblioteca.gestao.api.application.usuario.cadastrar.dto.CadastroUsuarioUseCaseInput;
import biblioteca.gestao.api.domain.ValidationException;
import biblioteca.gestao.api.domain.usuario.Usuario;
import biblioteca.gestao.api.infra.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroUsuarioUseCaseInput input, UriComponentsBuilder uriBuilder) throws ValidationException {
        CadastrarUsuarioUseCaseOutPut output = cadastrarUsuarioUseCase.execute(input);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(output.id()).toUri();
        return ResponseEntity.created(uri).body(output);
    }




    @GetMapping
    public ResponseEntity<Page<ListarUsuarioDTO>> listar(Pageable paginacao){
        var page =  repository.findAllByAtivoTrue(paginacao).map(ListarUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        Usuario usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoUsuarioDTO(usuario));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizaUsuarioDTO usuarioDTO,@PathVariable Long id){
        Usuario usuario = repository.getReferenceById(id);
        usuario.atualizar(usuarioDTO);

        return ResponseEntity.ok(new DadosDetalhamentoUsuarioDTO(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Usuario usuario = repository.getReferenceById(id);
        usuario.excluir();
        return ResponseEntity.noContent().build();
    }
}
