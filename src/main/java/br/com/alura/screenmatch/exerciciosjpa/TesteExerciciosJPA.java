package br.com.alura.screenmatch.exerciciosjpa;

import br.com.alura.screenmatch.exerciciosjpa.model.Categoria;
import br.com.alura.screenmatch.exerciciosjpa.model.Pedido;
import br.com.alura.screenmatch.exerciciosjpa.model.Produto;
import br.com.alura.screenmatch.exerciciosjpa.repository.CategoriaRepository;
import br.com.alura.screenmatch.exerciciosjpa.repository.PedidoRepository;
import br.com.alura.screenmatch.exerciciosjpa.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// EXERCÍCIO 8: Classe para testar salvamento de dados
// @Component: Marca como componente gerenciado pelo Spring (permite injeção de dependência)
@Component
public class TesteExerciciosJPA {
    
    // PASSO IMPORTANTE: Injeção de dependência dos repositórios
    // @Autowired: Spring injeta automaticamente os repositórios
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    // Método para executar os testes
    public void executar() {
        System.out.println("\n========================================");
        System.out.println("EXERCÍCIOS JPA - TESTANDO PERSISTÊNCIA");
        System.out.println("========================================\n");
        
        // Criar e salvar Produto
        Produto produto = new Produto("Notebook Dell", 3500.00);
        produtoRepository.save(produto);
        System.out.println("✅ Produto salvo: " + produto);
        
        // Criar e salvar Categoria
        Categoria categoria = new Categoria("Eletrônicos");
        categoriaRepository.save(categoria);
        System.out.println("✅ Categoria salva: " + categoria);
        
        // Criar e salvar Pedido
        Pedido pedido = new Pedido(LocalDate.now());
        pedidoRepository.save(pedido);
        System.out.println("✅ Pedido salvo: " + pedido);
        
        System.out.println("\n========================================");
        System.out.println("LISTANDO TODOS OS DADOS DO BANCO");
        System.out.println("========================================\n");
        
        // Listar todos os produtos
        System.out.println("📦 PRODUTOS:");
        produtoRepository.findAll().forEach(System.out::println);
        
        // Listar todas as categorias
        System.out.println("\n📂 CATEGORIAS:");
        categoriaRepository.findAll().forEach(System.out::println);
        
        // Listar todos os pedidos
        System.out.println("\n🛒 PEDIDOS:");
        pedidoRepository.findAll().forEach(System.out::println);
        
        System.out.println("\n========================================");
        System.out.println("✅ TESTES CONCLUÍDOS COM SUCESSO!");
        System.out.println("========================================\n");
    }
}
