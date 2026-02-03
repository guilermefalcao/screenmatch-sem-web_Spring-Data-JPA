package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.Categoria;

/**
 * DTO (Data Transfer Object) - Objeto de Transferência de Dados
 * 
 * O QUE É UM DTO?
 * - É um objeto simples usado para transferir dados entre camadas
 * - Contém apenas os dados que queremos expor na API
 * - NÃO contém lógica de negócio
 * - NÃO contém relacionamentos complexos (sem episódios)
 * 
 * POR QUE USAR DTO?
 * 1. SEGURANÇA: Não expõe dados sensíveis da entidade
 * 2. PERFORMANCE: Não carrega relacionamentos desnecessários (episódios)
 * 3. CONTROLE: Define exatamente o que o front-end recebe
 * 4. DESACOPLAMENTO: Mudanças na entidade não afetam a API
 * 5. EVITA PROBLEMAS: Sem loops infinitos de serialização JSON
 * 
 * DIFERENÇA ENTRE ENTIDADE E DTO:
 * 
 * ENTIDADE (Serie.java):
 * - Representa tabela do banco
 * - Tem @Entity, @Id, @OneToMany
 * - Contém relacionamentos (episodios)
 * - Pode ter dados sensíveis
 * - Usada internamente
 * 
 * DTO (SerieDTO.java):
 * - Representa dados da API
 * - É um record simples
 * - SEM relacionamentos complexos
 * - Apenas dados públicos
 * - Exposta para o front-end
 * 
 * EXEMPLO DE CONVERSÃO:
 * Serie (banco) → SerieDTO (API)
 * 
 * Serie serie = repositorio.findById(1);
 * SerieDTO dto = new SerieDTO(
 *     serie.getId(),
 *     serie.getTitulo(),
 *     serie.getTotalTemporadas(),
 *     serie.getAvaliacao(),
 *     serie.getGenero(),
 *     serie.getAtores(),
 *     serie.getPoster(),
 *     serie.getSinopse()
 * );
 */
public record SerieDTO(
        Long id,
        String titulo,
        Integer totalTemporadas,
        Double avaliacao,
        Categoria genero,
        String atores,
        String poster,
        String sinopse
) {
    // Records em Java automaticamente geram:
    // - Construtor com todos os parâmetros
    // - Getters (id(), titulo(), totalTemporadas(), etc.)
    // - equals(), hashCode(), toString()
    // - São IMUTÁVEIS (não tem setters)
    
    // NÃO precisa criar getters/setters manualmente!
    // Record já faz isso automaticamente
}
