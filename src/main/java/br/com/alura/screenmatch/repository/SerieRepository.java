package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que extende JpaRepository para operações de banco de dados
// JpaRepository<Serie, Long>:
//   - Serie: Tipo da entidade que será gerenciada
//   - Long: Tipo da chave primária (id) da entidade
// 
// Ao estender JpaRepository, ganhamos automaticamente métodos como:
//   - save(serie): Salva ou atualiza uma série no banco
//   - findById(id): Busca uma série pelo ID
//   - findAll(): Retorna todas as séries
//   - delete(serie): Remove uma série do banco
//   - count(): Conta quantas séries existem
//   - E muitos outros métodos prontos!
//
// NÃO precisamos implementar nada! O Spring Data JPA cria a implementação automaticamente
public interface SerieRepository extends JpaRepository<Serie, Long> {
    // Aqui podemos adicionar métodos de consulta personalizados no futuro
    // Exemplo: List<Serie> findByGenero(Categoria genero);
}
