package br.com.alura.screenmatch.exerciciosjpa.model;

import jakarta.persistence.*;
import java.time.LocalDate;

// EXERCÍCIO 5: Classe Pedido como entidade JPA
@Entity
@Table(name = "pedidos")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate data;
    
    // Construtor padrão (obrigatório para JPA)
    public Pedido() {}
    
    // Construtor com parâmetros
    public Pedido(LocalDate data) {
        this.data = data;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public LocalDate getData() {
        return data;
    }
    
    @Override
    public String toString() {
        return "Pedido{id=" + id + ", data=" + data + "}";
    }
}
