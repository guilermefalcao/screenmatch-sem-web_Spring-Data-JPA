package br.com.alura.screenmatch.exerciciosjpa.repository;

import br.com.alura.screenmatch.exerciciosjpa.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

// EXERCÍCIO 7: Repository para Pedido
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
