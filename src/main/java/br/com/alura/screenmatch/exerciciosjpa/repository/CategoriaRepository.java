package br.com.alura.screenmatch.exerciciosjpa.repository;

import br.com.alura.screenmatch.exerciciosjpa.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

// EXERCÍCIO 7: Repository para Categoria
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
