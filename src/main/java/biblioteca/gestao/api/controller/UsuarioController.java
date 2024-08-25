package biblioteca.gestao.api.controller;

import biblioteca.gestao.api.DTO.AtualizaUsuarioDTO;
import biblioteca.gestao.api.DTO.CadastroUsuarioDTO;
import biblioteca.gestao.api.DTO.ListarUsuarioDTO;
import biblioteca.gestao.api.model.Usuario;
import biblioteca.gestao.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CadastroUsuarioDTO usuarioDTO){
        Usuario usuario = Usuario.from(usuarioDTO);
        repository.save(usuario);
    }

    @GetMapping
    public Page<ListarUsuarioDTO> listar(Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(ListarUsuarioDTO::new);
    }

    @PutMapping("{id}")
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizaUsuarioDTO usuarioDTO,@PathVariable Long id){
        Usuario usuario = repository.getReferenceById(id);
        usuario.atualizar(usuarioDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        Usuario usuario = repository.getReferenceById(id);
        usuario.excluir();
    }
}
