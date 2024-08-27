package biblioteca.gestao.api.domain.livro;

import biblioteca.gestao.api.domain.Validation;

public class ValidadorLivro extends Validation<Livro> {


    @Override
    public void validar(Livro livro) {

        if(!livro.getIsbn().matches("^\\d{9}[\\dX]$") && !livro.getIsbn().matches("^\\d{13}$")){
            erros.add("ISBN não é válido.");
            valido=false;
        }

    }
}
