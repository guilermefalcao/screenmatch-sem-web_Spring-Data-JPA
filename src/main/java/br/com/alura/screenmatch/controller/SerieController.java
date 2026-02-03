package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CONTROLLER REST - Camada de Apresentação
 * 
 * Esta classe é responsável por receber requisições HTTP e retornar respostas.
 * É a "porta de entrada" da aplicação web.
 * 
 * ANOTAÇÕES:
 * - @RestController: Combina @Controller + @ResponseBody
 *   - Indica que esta classe é um controller REST
 *   - Retorna dados diretamente (JSON/texto), não páginas HTML
 *   - Spring converte automaticamente objetos Java para JSON
 * 
 * - @GetMapping: Define um endpoint HTTP GET
 *   - Mapeia uma URL para um método Java
 *   - Exemplo: GET http://localhost:8080/series
 * 
 * - @Autowired: Injeção de dependência
 *   - Spring cria e injeta automaticamente o SerieRepository
 *   - Não precisa fazer "new SerieRepository()"
 * 
 * FLUXO DE REQUISIÇÃO COM DTO:
 * 1. Cliente faz requisição: GET http://localhost:8080/series
 * 2. Spring identifica o @GetMapping("/series")
 * 3. Spring chama o método obterSeries()
 * 4. Método busca todas as séries no banco (repositorio.findAll())
 * 5. Converte cada Serie (entidade) para SerieDTO usando stream().map()
 * 6. Spring converte List<SerieDTO> para JSON automaticamente
 * 7. Cliente recebe resposta HTTP 200 com JSON (SEM episódios)
 * 
 * POR QUE USAR DTO?
 * - Evita expor relacionamentos complexos (episódios)
 * - Evita loop infinito de serialização JSON
 * - Controla exatamente quais dados são expostos
 * - Melhora performance (não carrega episódios)
 * - Desacopla API da estrutura do banco
 * 
 * EXEMPLO DE RESPOSTA JSON:
 * [
 *   {
 *     "id": 1,
 *     "titulo": "Breaking Bad",
 *     "totalTemporadas": 5,
 *     "avaliacao": 9.5,
 *     "genero": "DRAMA",
 *     "atores": "Bryan Cranston, Aaron Paul",
 *     "poster": "https://...",
 *     "sinopse": "Um professor..."
 *   },
 *   { ... }
 * ]
 * 
 * NOTA: Episódios NÃO aparecem no JSON!
 */
@RestController
public class SerieController {

    // @Autowired: Injeção de dependência do Spring
    // Spring cria automaticamente uma instância de SerieRepository e injeta aqui
    @Autowired
    private SerieRepository repositorio;

    /**
     * Endpoint GET /inicio
     * 
     * Endpoint simples para TESTAR o DevTools (hot reload automático).
     * Retorna apenas uma mensagem de texto.
     * 
     * OBJETIVO:
     * - Testar se DevTools está funcionando
     * - Verificar hot reload automático
     * - Endpoint de boas-vindas da API
     * 
     * COMO TESTAR DEVTOOLS:
     * 1. Inicie a aplicação: mvn spring-boot:run
     * 2. Acesse: http://localhost:8080/inicio
     * 3. Veja a mensagem: "Bem-vindo ao Screenmatch!"
     * 4. Altere a mensagem abaixo (ex: "Bem-vindo ao teste do DevTools!")
     * 5. Salve o arquivo (Ctrl+S)
     * 6. Aguarde 2-5 segundos (DevTools reinicia automaticamente)
     * 7. Atualize o navegador (F5)
     * 8. Veja a nova mensagem!
     * 
     * SE FUNCIONOU:
     * ✅ DevTools está configurado corretamente
     * ✅ Hot reload automático está ativo
     * ✅ Não precisa parar/iniciar aplicação manualmente
     * 
     * @return Mensagem de boas-vindas (texto simples)
     * 
     * TESTES:
     * - Navegador: http://localhost:8080/inicio
     * - Postman: GET http://localhost:8080/inicio
     * - cURL: curl http://localhost:8080/inicio
     * 
     * RESPOSTA:
     * - HTTP 200 OK
     * - Content-Type: text/plain
     * - Body: "Bem-vindo ao Screenmatch!"
     */
    @GetMapping("/inicio")
    public String inicio() {
        // Retorna mensagem simples de texto
        // Altere esta mensagem para testar o DevTools!
        return "Bem-vindo ao Screenmatch!";
    }

    /**
     * Endpoint GET /series
     * 
     * Retorna todas as séries cadastradas no banco de dados em formato JSON.
     * Usa DTO para expor apenas dados necessários (SEM episódios).
     * 
     * @return Lista de SerieDTO (convertida automaticamente para JSON)
     * 
     * TESTES:
     * - Navegador: http://localhost:8080/series
     * - Postman: GET http://localhost:8080/series
     * - cURL: curl http://localhost:8080/series
     * 
     * RESPOSTA:
     * - HTTP 200 OK
     * - Content-Type: application/json
     * - Body: [{"id":1,"titulo":"Breaking Bad",...}, {...}]
     * - SEM campo "episodios" (controlado pelo DTO)
     * 
     * CONVERSÃO Serie → SerieDTO:
     * 1. repositorio.findAll() → List<Serie> (entidades do banco)
     * 2. .stream() → Stream<Serie> (para processar cada elemento)
     * 3. .map(s -> new SerieDTO(...)) → Stream<SerieDTO> (converte cada Serie em SerieDTO)
     * 4. .collect(Collectors.toList()) → List<SerieDTO> (coleta em lista)
     * 5. Spring converte List<SerieDTO> para JSON
     */
    @GetMapping("/series")
    public List<SerieDTO> obterSeries() {
        // 1. Busca todas as séries do banco (List<Serie>)
        // 2. Converte cada Serie para SerieDTO usando stream + map
        // 3. Coleta resultado em List<SerieDTO>
        return repositorio.findAll()
                .stream()
                .map(s -> new SerieDTO(
                        s.getId(),              // Long id
                        s.getTitulo(),          // String titulo
                        s.getTotalTemporadas(), // Integer totalTemporadas
                        s.getAvaliacao(),       // Double avaliacao
                        s.getGenero(),          // Categoria genero
                        s.getAtores(),          // String atores
                        s.getPoster(),          // String poster
                        s.getSinopse()          // String sinopse
                ))
                .collect(Collectors.toList());
        
        // NOTA: Episódios NÃO são incluídos no DTO!
        // Isso evita:
        // - Loop infinito de serialização
        // - Carregar dados desnecessários
        // - Expor estrutura interna do banco
    }

}
