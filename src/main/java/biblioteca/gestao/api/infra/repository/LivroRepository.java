package biblioteca.gestao.api.infra.repository;

import biblioteca.gestao.api.domain.livro.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Page<Livro> findAllByAtivoTrue(Pageable paginacao);
}
