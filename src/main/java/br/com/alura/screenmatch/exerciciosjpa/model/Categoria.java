package br.com.alura.screenmatch.exerciciosjpa.model;

import jakarta.persistence.*;

// EXERCÍCIO 4: Classe Categoria como entidade JPA
@Entity
@Table(name = "categorias")
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    // Construtor padrão (obrigatório para JPA)
    public Categoria() {}
    
    // Construtor com parâmetros
    public Categoria(String nome) {
        this.nome = nome;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    @Override
    public String toString() {
        return "Categoria{id=" + id + ", nome='" + nome + "'}";
    }
}
