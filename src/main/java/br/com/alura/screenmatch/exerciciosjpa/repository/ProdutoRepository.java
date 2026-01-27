package br.com.alura.screenmatch.exerciciosjpa.repository;

import br.com.alura.screenmatch.exerciciosjpa.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

// EXERCÍCIO 7: Repository para Produto
// Herda de JpaRepository<Entidade, TipoDoId>
// Métodos automáticos: save(), findAll(), findById(), delete(), count()
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
