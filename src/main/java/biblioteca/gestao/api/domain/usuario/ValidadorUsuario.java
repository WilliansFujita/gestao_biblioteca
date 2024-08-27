package biblioteca.gestao.api.domain.usuario;

import biblioteca.gestao.api.domain.Validation;

import java.util.Date;

public class ValidadorUsuario extends Validation<Usuario> {


    public static final String EMAIL_INVALIDO = "Email inválido.";
    public static final String DATA_DE_CADASTRO_NAO_PODE_SER_MAIOR_QUE_A_DATA_ATUAL = "Data de cadastro não pode ser maior que a data atual.";

    @Override
    public void validar(Usuario usuario) {
        if(!usuario.getEmail().matches("^(.+)@(\\S+)$")){
            erros.add(EMAIL_INVALIDO);
            valido = false;
        }

        if(usuario.getDataCadastro().after(new Date())){
            erros.add(DATA_DE_CADASTRO_NAO_PODE_SER_MAIOR_QUE_A_DATA_ATUAL);
            valido = false;
        }
    }

}
