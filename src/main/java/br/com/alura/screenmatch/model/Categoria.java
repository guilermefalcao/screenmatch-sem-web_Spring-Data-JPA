package br.com.alura.screenmatch.model;

// Enum que representa as categorias/gêneros de séries
public enum Categoria {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    SUSPENSE("Thriller", "Suspense"),
    TERROR("Horror", "Terror"),
    FICCAO("Sci-Fi", "Ficção Científica"),
    FANTASIA("Fantasy", "Fantasia"),
    AVENTURA("Adventure", "Aventura"),
    ANIMACAO("Animation", "Animação"),
    DOCUMENTARIO("Documentary", "Documentário");

    // Categoria em inglês (como vem da API)
    private String categoriaOmdb;
    // Categoria em português
    private String categoriaPortugues;

    // Construtor do enum
    Categoria(String categoriaOmdb, String categoriaPortugues) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    // Método para converter String da API para o enum Categoria
    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    // Método para converter String em português para o enum Categoria
    public static Categoria fromPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public String getCategoriaOmdb() {
        return categoriaOmdb;
    }

    public String getCategoriaPortugues() {
        return categoriaPortugues;
    }
}
