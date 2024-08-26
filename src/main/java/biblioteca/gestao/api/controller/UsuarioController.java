package biblioteca.gestao.api.controller;

import biblioteca.gestao.api.DTO.usuario.AtualizaUsuarioDTO;
import biblioteca.gestao.api.DTO.usuario.CadastroUsuarioDTO;
import biblioteca.gestao.api.DTO.usuario.DadosDetalhamentoUsuarioDTO;
import biblioteca.gestao.api.DTO.usuario.ListarUsuarioDTO;
import biblioteca.gestao.api.model.Usuario;
import biblioteca.gestao.api.repository.UsuarioRepository;
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

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroUsuarioDTO usuarioDTO, UriComponentsBuilder uriBuilder){
        Usuario usuario = Usuario.from(usuarioDTO);
        repository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuarioDTO(usuario));
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
