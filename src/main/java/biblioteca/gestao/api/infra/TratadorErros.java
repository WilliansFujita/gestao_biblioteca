package biblioteca.gestao.api.infra;

import biblioteca.gestao.api.domain.DomainException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldError();
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity tratarErroDomain(DomainException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
