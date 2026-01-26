package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import br.com.alura.screenmatch.service.traducao.ConsultaMyMemory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

// Classe que representa uma série de TV
// @Entity: Marca esta classe como uma ENTIDADE JPA (será mapeada para uma tabela no banco)
@Entity
// @Table: Define o nome da tabela no banco (se não usar, o nome será o nome da classe)
@Table(name = "series")
public class Serie {
    
    // ========================================
    // ATRIBUTOS DA ENTIDADE
    // ========================================
    
    // @Id: Define este campo como CHAVE PRIMÁRIA da tabela
    @Id
    // @GeneratedValue: O banco gera o valor automaticamente (auto-increment)
    // IDENTITY: Usa a estratégia de auto-incremento do próprio banco (SERIAL no PostgreSQL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(unique = true): Este campo deve ser Único no banco (não pode ter títulos duplicados)
    @Column(unique = true)
    private String titulo;

    private Integer totalTemporadas;
    private Double avaliacao;

    // @Enumerated: Define como o enum será armazenado no banco
    // EnumType.STRING: Salva o NOME do enum (ex: "ACAO", "COMEDIA")
    // EnumType.ORDINAL: Salvaria o ÍNDICE numérico (0, 1, 2...) - NÃO recomendado!
    @Enumerated(EnumType.STRING)
    private Categoria genero;

    private String atores;
    private String poster;
    private String sinopse;

    // @Transient: Indica que este campo NÃO será persistido no banco de dados
    // Usado para campos temporários ou calculados que existem apenas em memória
    // Neste caso, a lista de episódios será carregada dinamicamente, não salva diretamente
    @Transient
    private List<Episodio> episodios = new ArrayList<>();

    // ========================================
    // CONSTRUTORES
    // ========================================
    
    // Construtor padrão vazio (OBRIGATÓRIO para o JPA funcionar!)
    public Serie() {}

    // Construtor que recebe um objeto DadosSerie e converte para Serie
    public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        // Tenta converter a avaliação de String para Double
        // Se a conversão falhar (valor inválido ou "N/A"), atribui 0.0
        // OptionalDouble.of() cria um Optional com o valor convertido
        // orElse(0) retorna 0 caso ocorra algum erro na conversão
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
        // Converte a String do gênero para o enum Categoria
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        this.atores = dadosSerie.atores();
        this.poster = dadosSerie.poster();
        // Traduz a sinopse usando MyMemory API (gratuita, 5000 caracteres/dia)
        this.sinopse = ConsultaMyMemory.obterTraducao(dadosSerie.sinopse()).trim();
    }


    
    @Transient //anotaçao para um objeto q nao vai ser salvo, apenas criar, nao vai representar no banco
    private List<Episodio> epsodios = new ArrayList<>();

    // ========================================
    // GETTERS E SETTERS
    // ========================================

    //falta os GETTERS E SETTERS de episodio



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }

    // Método toString para exibir as informações da série
    @Override
    public String toString() {
        return "genero='" + genero + '\'' +
                ", titulo='" + titulo + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", avaliacao=" + avaliacao +
                ", atores='" + atores + '\'' +
                ", poster='" + poster + '\'' +
                ", sinopse='" + sinopse + '\'';
    }
}
