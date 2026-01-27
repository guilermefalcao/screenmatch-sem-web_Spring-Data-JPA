package br.com.alura.screenmatch.exerciciosjpa.model;

import jakarta.persistence.*;

// EXERCÍCIOS 1, 2 e 3: Classe Produto com mapeamento JPA completo
@Entity
@Table(name = "produtos")
public class Produto {
    
    // EXERCÍCIO 1: Chave primária com @Id
    // EXERCÍCIO 2: @GeneratedValue com IDENTITY (auto-increment do banco)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // EXERCÍCIO 3: @Column com unique=true (não permite duplicados) e nullable=false (obrigatório)
    @Column(unique = true, nullable = false)
    private String nome;
    
    // EXERCÍCIO 3: @Column com name="valor" (nome da coluna no banco será "valor" em vez de "preco")
    @Column(name = "valor")
    private Double preco;
    
    // Construtor padrão (obrigatório para JPA)
    public Produto() {}
    
    // Construtor com parâmetros
    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public Double getPreco() {
        return preco;
    }
    
    @Override
    public String toString() {
        return "Produto{id=" + id + ", nome='" + nome + "', preco=" + preco + "}";
    }
}
