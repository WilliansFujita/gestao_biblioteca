package biblioteca.gestao.api.infra.repository;

import biblioteca.gestao.api.domain.livro.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Page<Livro> findAllByAtivoTrue(Pageable paginacao);

    @Query("SELECT l FROM Livro l WHERE l.id NOT IN (SELECT e.livro.id FROM Emprestimo e WHERE e.usuario.id = :usuarioId)")
    List<Livro> buscarLivrosNaoEmprestados(@Param("usuarioId") Long usuarioId);
}
