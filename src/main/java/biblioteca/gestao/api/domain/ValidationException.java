package biblioteca.gestao.api.domain;

import java.util.List;

public class ValidationException extends Exception{

    public ValidationException(List<String> erros) {
        super(erros.toString());
    }
}
