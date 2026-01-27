package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.exercicios.ExerciciosResolvidos;
import br.com.alura.screenmatch.exerciciosjpa.TesteExerciciosJPA;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    
    // 🔒 SEGURANÇA: API Key agora vem da variável de ambiente OMDB_API_KEY
    // Se a variável não existir, usa uma string vazia (evita erro de compilação)
    private final String API_KEY = "&apikey=" + System.getenv("OMDB_API_KEY");

    private List<DadosSerie> dadosSeries = new ArrayList<>();
    private List<Episodio> episodios = new ArrayList<>();

    // Repositório para acessar o banco de dados
    private SerieRepository repositorio;
    
    // Teste dos exercícios JPA
    private TesteExerciciosJPA testeExerciciosJPA;

    // Construtor que recebe o repositório por injeção de dependência
    // O Spring passa automaticamente o repositório quando cria esta classe
    public Principal(SerieRepository repositorio, TesteExerciciosJPA testeExerciciosJPA) {
        this.repositorio = repositorio;
        this.testeExerciciosJPA = testeExerciciosJPA;
    }


    public void exibeMenu() {
        var opcao = -1;

        while (opcao !=0) {
            
        
        var menu = """
                1 - Buscar séries
                2 - Buscar episódios
                3 - Listar series buscadas
                
                4 - Exercícios resolvidos
                5 - Testar Exercícios JPA (Produto, Categoria, Pedido)
                
                0 - Sair                                 
                """;

        System.out.println(menu);
        opcao = leitura.nextInt();
        leitura.nextLine();

        switch (opcao) {
            case 1:
                buscarSerieWeb();
                break;
            case 2:
                buscarEpisodioPorSerie();
                break;
            case 3:
                ListarSeriesBuscadas();  //aula 1 criado o metodo para listar series buscadas
                break;
            case 4:
                ExerciciosResolvidos.executarTodos();
                break;
            case 5:
                testeExerciciosJPA.executar();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
           
          
            default:
                System.out.println("Opção inválida");
        }
    }
    }

    private void buscarSerieWeb() {
        // 1. Busca os dados da série na API OMDB
        DadosSerie dados = getDadosSerie();
        
        // 2. Converte os dados da API para um objeto Serie (entidade JPA)
        Serie serie = new Serie(dados);
        
        // 3. Salva a série no banco de dados usando o repositório
        // O método save() insere um novo registro ou atualiza se já existir
        repositorio.save(serie);
        
        // 4. Exibe os dados no console
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie(){
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumo.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }

private void ListarSeriesBuscadas() {
    // 1. Cria uma lista vazia de objetos Serie
    List<Serie> series = repositorio.findAll();  //vai pegar no repositorio e trazer todos do banco

    
    
    // 2. Transforma a lista de DadosSerie em lista de Serie
    //series = dadosSeries.stream()           // Cria um stream da lista dadosSeries
           //.map(d -> new Serie(d))          // Para cada DadosSerie (d), cria um novo objeto Serie
          // .collect(Collectors.toList());   // Coleta todos os objetos Serie em uma lista

    // 3. Ordena e exibe as séries
    series.stream()                         // Cria um novo stream da lista series
        .sorted(Comparator.comparing(Serie::getGenero))  // Ordena por gênero (categoria)
        .forEach(System.out::println);      // Imprime cada série no console
}

            
      
            
        }
    





