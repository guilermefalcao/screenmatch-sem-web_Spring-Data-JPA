package br.com.alura.screenmatch.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.cert.X509Certificate;
import java.time.Duration;

public class ConsultaChatGPT {
    
    // IMPORTANTE: Substitua pela sua chave da OpenAI ou configure como variável de ambiente
    private static final String API_KEY = System.getenv("OPENAI_API_KEY") != null ? 
            System.getenv("OPENAI_API_KEY") : "sua-chave-openai-aqui";
    private static final String API_URL = "https://api.openai.com/v1/completions";
    
    public static String obterTraducao(String texto) {
        try {
            // Cria HttpClient que ignora validação SSL (igual projeto FIPE)
            HttpClient client = HttpClient.newBuilder()
                    .sslContext(createInsecureSSLContext())
                    .connectTimeout(Duration.ofSeconds(30))
                    .build();
            
            // Monta o JSON da requisição
            String textoEscapado = texto.replace("\"", "\\\"").replace("\n", " ");
            String jsonBody = "{" +
                    "\"model\": \"gpt-3.5-turbo-instruct\"," +
                    "\"prompt\": \"traduza para o português o texto: " + textoEscapado + "\"," +
                    "\"max_tokens\": 1000," +
                    "\"temperature\": 0.7" +
                    "}";
            
            // Cria a requisição HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();
            
            System.out.println("[INFO] Enviando requisição para OpenAI...");
            
            // Envia a requisição
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // Processa a resposta
            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.body());
                String traducao = root.path("choices").get(0).path("text").asText().trim();
                System.out.println("[INFO] Tradução recebida com sucesso!");
                return traducao;
            } else {
                System.err.println("[ERRO] API retornou status: " + response.statusCode());
                System.err.println("[ERRO] Resposta: " + response.body());
                return texto;
            }
            
        } catch (Exception e) {
            System.err.println("[ERRO] Falha ao traduzir: " + e.getMessage());
            return texto;
        }
    }
    
    // Método para criar SSLContext que ignora validação de certificados
    // Necessário para contornar problemas de SSL em ambientes corporativos
    private static SSLContext createInsecureSSLContext() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
            };
            
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return sslContext;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar SSLContext", e);
        }
    }
}
