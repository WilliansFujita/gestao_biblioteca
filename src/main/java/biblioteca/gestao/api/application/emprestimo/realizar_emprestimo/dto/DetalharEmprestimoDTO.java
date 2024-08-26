package biblioteca.gestao.api.application.emprestimo.realizar_emprestimo.dto;

import biblioteca.gestao.api.domain.emprestimo.Emprestimo;

import java.util.Date;

public record DetalharEmprestimoDTO(
        Long id,
        Date data_emprestimo,
        Date data_devolucao,
        Long usuario_id,
        Long livro_id,
        boolean status
) {
    public DetalharEmprestimoDTO(Emprestimo emprestimo) {
        this(emprestimo.getId(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao(),
                emprestimo.getUsuario().getId(),
                emprestimo.getLivro().getId(),
                emprestimo.isStatus());
    }
}
