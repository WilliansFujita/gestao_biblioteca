package biblioteca.gestao.api.infra.repository;

import biblioteca.gestao.api.domain.emprestimo.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    //Emprestimo findByIdLivroAndStatusTrue(Long id_livro);

    Emprestimo findByLivroId(Long aLong);
}
