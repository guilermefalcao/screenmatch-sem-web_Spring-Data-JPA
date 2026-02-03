package br.com.alura.screenmatch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CONFIGURAÇÃO CORS (Cross-Origin Resource Sharing)
 * 
 * O QUE É CORS?
 * CORS é um mecanismo de segurança dos navegadores que BLOQUEIA requisições
 * de um domínio (origem) diferente do servidor.
 * 
 * PROBLEMA SEM CORS:
 * - Front-end roda em: http://127.0.0.1:5501 (Live Server)
 * - Back-end roda em: http://localhost:8080 (Spring Boot)
 * - Navegador BLOQUEIA a requisição por serem origens diferentes!
 * 
 * ERRO NO CONSOLE DO NAVEGADOR (sem CORS):
 * "Access to fetch at 'http://localhost:8080/series' from origin 
 * 'http://127.0.0.1:5501' has been blocked by CORS policy: 
 * No 'Access-Control-Allow-Origin' header is present."
 * 
 * SOLUÇÃO:
 * Configurar CORS no back-end para AUTORIZAR o front-end a fazer requisições.
 * 
 * COMO FUNCIONA:
 * 1. Front-end (http://127.0.0.1:5501) faz requisição para back-end
 * 2. Navegador verifica se origem está autorizada (CORS)
 * 3. Back-end responde com header: Access-Control-Allow-Origin: http://127.0.0.1:5501
 * 4. Navegador PERMITE a requisição
 * 
 * ANOTAÇÕES:
 * - @Configuration: Marca como classe de configuração do Spring
 * - WebMvcConfigurer: Interface para configurar Spring MVC
 * - addCorsMappings(): Método para configurar CORS
 * 
 * CONFIGURAÇÕES:
 * - addMapping("/**"): Aplica CORS em TODAS as rotas (/series, /episodios, etc.)
 * - allowedOrigins(): Quais domínios podem acessar (front-end)
 * - allowedMethods(): Quais métodos HTTP são permitidos (GET, POST, etc.)
 * 
 * EXEMPLO PRÁTICO:
 * Sem CORS: index.html não consegue fazer fetch('http://localhost:8080/series')
 * Com CORS: index.html PODE fazer fetch e receber os dados!
 * 
 * SEGURANÇA:
 * - NUNCA use "*" em produção (allowedOrigins("*"))
 * - Sempre especifique as origens exatas
 * - Em produção: allowedOrigins("https://meusite.com")
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    /**
     * Configura CORS para permitir que o front-end acesse o back-end
     * 
     * @param registry Registro de configurações CORS
     * 
     * CONFIGURAÇÕES:
     * 
     * 1. addMapping("/**")
     *    - Aplica CORS em TODAS as rotas do back-end
     *    - /** = qualquer endpoint (/series, /series/1, /episodios, etc.)
     * 
     * 2. allowedOrigins("http://127.0.0.1:5501")
     *    - Autoriza APENAS este domínio a acessar o back-end
     *    - http://127.0.0.1:5501 = Live Server do VS Code (porta padrão)
     *    - Se front-end rodar em outra porta, adicione: .allowedOrigins("http://127.0.0.1:5501", "http://localhost:3000")
     * 
     * 3. allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
     *    - GET: Buscar dados (listar séries)
     *    - POST: Criar dados (adicionar série)
     *    - PUT: Atualizar dados (editar série)
     *    - DELETE: Remover dados (deletar série)
     *    - OPTIONS: Pré-flight request (navegador verifica permissões antes de enviar requisição real)
     *    - HEAD, TRACE, CONNECT: Métodos HTTP menos comuns
     * 
     * EXEMPLO DE USO NO FRONT-END (index.html):
     * 
     * fetch('http://localhost:8080/series')
     *   .then(response => response.json())
     *   .then(data => console.log(data))  // Funciona! CORS autorizado
     *   .catch(error => console.error(error));
     * 
     * SEM CORS:
     * - Navegador bloqueia requisição
     * - Erro no console: "blocked by CORS policy"
     * - Front-end não recebe dados
     * 
     * COM CORS:
     * - Navegador permite requisição
     * - Front-end recebe dados do back-end
     * - Aplicação funciona!
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica em todas as rotas
                .allowedOrigins("http://127.0.0.1:5501")  // Autoriza Live Server
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");  // Métodos permitidos
    }
}
