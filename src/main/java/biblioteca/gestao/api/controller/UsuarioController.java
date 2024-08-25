package biblioteca.gestao.api.controller;

import biblioteca.gestao.api.DTO.CadastroUsuarioDTO;
import biblioteca.gestao.api.model.Usuario;
import biblioteca.gestao.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
}
