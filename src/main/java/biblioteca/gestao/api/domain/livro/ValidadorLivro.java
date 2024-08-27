package biblioteca.gestao.api.domain.livro;

import biblioteca.gestao.api.domain.Validation;

public class ValidadorLivro extends Validation<Livro> {


    @Override
    public void validar(Livro livro) {

        if(!livro.getIsbn().matches( "^(?=(?:[^0-9]*[0-9]){10}(?:(?:[^0-9]*[0-9]){3})?$)[\\d-]+$")){
            erros.add("ISBN não é válido.");
            valido=false;
        }

    }
}
