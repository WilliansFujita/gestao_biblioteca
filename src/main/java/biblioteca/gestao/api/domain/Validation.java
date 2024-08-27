package biblioteca.gestao.api.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Validation<T> {
    protected List<String> erros = new ArrayList<>();
    protected boolean valido = true;

    public abstract void validar(T objetoValidacao);

    public List<String> getErros() {
        return erros;
    }

    public boolean isValido() {
        return valido;
    }
}
