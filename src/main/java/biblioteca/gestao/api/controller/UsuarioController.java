package biblioteca.gestao.api.controller;

import biblioteca.gestao.api.DTO.CadastroUsuarioDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @PostMapping
    public void cadastrarUsuario(@RequestBody CadastroUsuarioDTO usuarioDTO){

    }
}
