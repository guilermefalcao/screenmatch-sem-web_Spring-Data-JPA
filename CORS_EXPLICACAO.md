# 🔒 Guia Completo: CORS (Cross-Origin Resource Sharing)

## 🤔 O que é CORS?

**CORS** é um mecanismo de **segurança dos navegadores** que controla quais sites podem acessar recursos de outros domínios.

---

## 🚫 Problema: Sem CORS

### Cenário

```
┌─────────────────────────────────────┐
│  FRONT-END (index.html)             │
│  http://127.0.0.1:5501              │
│                                     │
│  fetch('http://localhost:8080/series')
│         ↓                           │
└─────────┼───────────────────────────┘
          │
          │ ❌ BLOQUEADO PELO NAVEGADOR!
          │
          ↓
┌─────────────────────────────────────┐
│  BACK-END (Spring Boot)             │
│  http://localhost:8080              │
│                                     │
│  @GetMapping("/series")             │
└─────────────────────────────────────┘
```

### Erro no Console do Navegador

```
Access to fetch at 'http://localhost:8080/series' 
from origin 'http://127.0.0.1:5501' 
has been blocked by CORS policy: 
No 'Access-Control-Allow-Origin' header is present 
on the requested resource.
```

### Por que isso acontece?

**Origens diferentes:**
- Front-end: `http://127.0.0.1:5501` (Live Server)
- Back-end: `http://localhost:8080` (Spring Boot)

**Navegador bloqueia por segurança!**

---

## ✅ Solução: Configurar CORS

### Cenário com CORS

```
┌─────────────────────────────────────┐
│  FRONT-END (index.html)             │
│  http://127.0.0.1:5501              │
│                                     │
│  fetch('http://localhost:8080/series')
│         ↓                           │
└─────────┼───────────────────────────┘
          │
          │ 1. Navegador verifica CORS
          │
          ↓
┌─────────────────────────────────────┐
│  BACK-END (Spring Boot)             │
│  http://localhost:8080              │
│                                     │
│  CorsConfiguration:                 │
│  ✅ allowedOrigins("http://127.0.0.1:5501")
│                                     │
│  Resposta com header:               │
│  Access-Control-Allow-Origin:       │
│  http://127.0.0.1:5501              │
│         ↓                           │
└─────────┼───────────────────────────┘
          │
          │ 2. Navegador PERMITE!
          │
          ↓
┌─────────────────────────────────────┐
│  FRONT-END recebe dados             │
│  [{"id":1,"titulo":"Breaking Bad"}] │
└─────────────────────────────────────┘
```

---

## 📋 Configuração Detalhada

### CorsConfiguration.java

```java
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // ← Todas as rotas
                .allowedOrigins("http://127.0.0.1:5501")  // ← Quem pode acessar
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");  // ← O que pode fazer
    }
}
```

### Explicação de Cada Parte

| Configuração | O que faz | Exemplo |
|--------------|-----------|---------|
| `addMapping("/**")` | Define quais rotas têm CORS | `/**` = todas (/series, /episodios, etc.) |
| `allowedOrigins()` | Quais domínios podem acessar | `http://127.0.0.1:5501` (Live Server) |
| `allowedMethods()` | Quais métodos HTTP são permitidos | GET, POST, PUT, DELETE |

---

## 🌐 Entendendo Origens (Origins)

### O que é uma Origem?

**Origem = Protocolo + Domínio + Porta**

| URL | Protocolo | Domínio | Porta | Origem Completa |
|-----|-----------|---------|-------|-----------------|
| `http://127.0.0.1:5501` | http | 127.0.0.1 | 5501 | `http://127.0.0.1:5501` |
| `http://localhost:8080` | http | localhost | 8080 | `http://localhost:8080` |
| `https://meusite.com` | https | meusite.com | 443 | `https://meusite.com` |

### Origens Diferentes (CORS necessário)

```
http://127.0.0.1:5501  ≠  http://localhost:8080
   ↑                          ↑
Front-end                  Back-end
```

**Mesmo que estejam no mesmo computador, são origens DIFERENTES!**

---

## 🔧 Métodos HTTP Permitidos

| Método | Função | Exemplo de Uso |
|--------|--------|----------------|
| **GET** | Buscar dados | Listar séries |
| **POST** | Criar dados | Adicionar nova série |
| **PUT** | Atualizar dados | Editar série existente |
| **DELETE** | Remover dados | Deletar série |
| **OPTIONS** | Pré-flight request | Navegador verifica permissões antes de enviar requisição real |
| HEAD | Buscar apenas headers | Verificar se recurso existe |
| TRACE | Debug | Raramente usado |
| CONNECT | Túnel | Raramente usado |

### O que é OPTIONS (Pré-flight)?

Antes de enviar uma requisição POST/PUT/DELETE, o navegador faz uma requisição OPTIONS para verificar se tem permissão:

```
1. Navegador: OPTIONS http://localhost:8080/series
   ↓
2. Back-end: "Sim, você pode fazer POST/PUT/DELETE"
   ↓
3. Navegador: POST http://localhost:8080/series (dados da série)
   ↓
4. Back-end: "Série criada com sucesso!"
```

---

## 🧪 Testando CORS

### 1. Sem CORS (Erro)

**index.html:**
```html
<script>
  fetch('http://localhost:8080/series')
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error('❌ Erro CORS:', error));
</script>
```

**Console do Navegador:**
```
❌ Erro CORS: TypeError: Failed to fetch
Access to fetch at 'http://localhost:8080/series' 
from origin 'http://127.0.0.1:5501' has been blocked by CORS policy
```

---

### 2. Com CORS (Funciona)

**CorsConfiguration.java configurado**

**index.html:**
```html
<script>
  fetch('http://localhost:8080/series')
    .then(response => response.json())
    .then(data => {
      console.log('✅ Dados recebidos:', data);
      // [{"id":1,"titulo":"Breaking Bad",...}]
    })
    .catch(error => console.error('Erro:', error));
</script>
```

**Console do Navegador:**
```
✅ Dados recebidos: 
[
  {id: 1, titulo: "Breaking Bad", totalTemporadas: 5, ...},
  {id: 2, titulo: "The Boys", totalTemporadas: 4, ...}
]
```

---

## 🔒 Segurança: Boas Práticas

### ❌ NÃO FAÇA (Inseguro)

```java
// NUNCA use "*" em produção!
.allowedOrigins("*")  // Qualquer site pode acessar seu back-end
```

**Problema:** Qualquer site malicioso pode fazer requisições ao seu back-end!

---

### ✅ FAÇA (Seguro)

```java
// Especifique as origens exatas
.allowedOrigins(
    "http://127.0.0.1:5501",      // Desenvolvimento (Live Server)
    "http://localhost:3000",       // Desenvolvimento (React)
    "https://meusite.com"          // Produção
)
```

---

## 🌍 Configurações por Ambiente

### Desenvolvimento

```java
.allowedOrigins(
    "http://127.0.0.1:5501",  // Live Server
    "http://localhost:3000",   // React
    "http://localhost:4200"    // Angular
)
```

### Produção

```java
.allowedOrigins(
    "https://meusite.com",
    "https://www.meusite.com"
)
```

### Usando Variáveis de Ambiente

```java
@Value("${cors.allowed.origins}")
private String allowedOrigins;

@Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins(allowedOrigins.split(","))
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
}
```

**application.properties:**
```properties
# Desenvolvimento
cors.allowed.origins=http://127.0.0.1:5501,http://localhost:3000

# Produção
cors.allowed.origins=https://meusite.com
```

---

## 🐛 Troubleshooting

### Problema 1: Ainda dá erro CORS

**Possíveis causas:**
1. Porta do Live Server diferente (5501 vs 5500)
2. Esqueceu de reiniciar o Spring Boot
3. Origem escrita errada (http vs https)

**Solução:**
```java
// Adicione múltiplas portas
.allowedOrigins(
    "http://127.0.0.1:5500",
    "http://127.0.0.1:5501",
    "http://localhost:5500",
    "http://localhost:5501"
)
```

---

### Problema 2: OPTIONS retorna 403 Forbidden

**Causa:** Spring Security bloqueando OPTIONS

**Solução:**
```java
// Se usar Spring Security, permita OPTIONS
http.authorizeRequests()
    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
```

---

### Problema 3: Funciona no Postman mas não no navegador

**Explicação:** Postman NÃO verifica CORS (é uma ferramenta, não um navegador)

**Solução:** Configure CORS corretamente para navegadores

---

## 📊 Resumo Visual

```
SEM CORS:
Front-end → ❌ BLOQUEADO → Back-end

COM CORS:
Front-end → ✅ AUTORIZADO → Back-end
```

---

## ✅ Checklist

- [ ] CorsConfiguration.java criado
- [ ] @Configuration adicionado
- [ ] WebMvcConfigurer implementado
- [ ] addCorsMappings() sobrescrito
- [ ] allowedOrigins() configurado com origem do front-end
- [ ] allowedMethods() inclui GET, POST, PUT, DELETE, OPTIONS
- [ ] Spring Boot reiniciado
- [ ] Teste no navegador funciona (sem erro CORS)

---

**Desenvolvido por:** Guilherme Falcão  
**Curso:** Alura - Formação Avançando com Java  
**Aula:** 04 - Desenvolvimento Web (CORS)
