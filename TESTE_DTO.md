# 🧪 Guia de Teste - Endpoint /series com DTO

## 📋 O que foi implementado

### 1. SerieDTO (Data Transfer Object)
- **Localização:** `dto/SerieDTO.java`
- **Função:** Expor apenas dados necessários na API (SEM episódios)
- **Tipo:** Record (imutável, com getters automáticos)

### 2. SerieController Atualizado
- **Retorno:** `List<SerieDTO>` (antes era `List<Serie>`)
- **Conversão:** Serie → SerieDTO usando `stream().map()`

---

## 🚀 Como Testar

### 1. Iniciar Aplicação

```bash
mvn spring-boot:run
```

**Console deve mostrar:**
```
Tomcat started on port(s): 8080 (http)
Started ScreenmatchApplication in X seconds
```

---

### 2. Testar no Navegador

**URL:**
```
http://localhost:8080/series
```

**Resposta Esperada (JSON):**
```json
[
  {
    "id": 1,
    "titulo": "Breaking Bad",
    "totalTemporadas": 5,
    "avaliacao": 9.5,
    "genero": "DRAMA",
    "atores": "Bryan Cranston, Aaron Paul",
    "poster": "https://m.media-amazon.com/images/...",
    "sinopse": "Um professor de química..."
  },
  {
    "id": 2,
    "titulo": "The Boys",
    "totalTemporadas": 4,
    "avaliacao": 8.7,
    "genero": "ACAO",
    "atores": "Karl Urban, Jack Quaid",
    "poster": "https://...",
    "sinopse": "Um grupo de vigilantes..."
  }
]
```

**✅ NOTA IMPORTANTE:** Campo `episodios` NÃO aparece mais!

---

### 3. Testar no Postman

**Configuração:**
- **Method:** GET
- **URL:** `http://localhost:8080/series`
- **Headers:** (automático)

**Clique em "Send"**

**Resposta:**
- **Status:** 200 OK
- **Content-Type:** application/json
- **Body:** Array de objetos SerieDTO

---

### 4. Testar com cURL (Terminal)

```bash
curl http://localhost:8080/series
```

**Ou formatado (com jq):**
```bash
curl http://localhost:8080/series | jq
```

---

## 🔍 Comparação: Antes vs Depois

### ❌ ANTES (retornava Serie - com episódios)

```json
{
  "id": 1,
  "titulo": "Breaking Bad",
  "totalTemporadas": 5,
  "avaliacao": 9.5,
  "genero": "DRAMA",
  "episodios": [
    {
      "id": 1,
      "titulo": "Pilot",
      "temporada": 1,
      "numeroEpisodio": 1,
      "avaliacao": 9.0
    },
    // ... mais 61 episódios
  ]
}
```

**Problemas:**
- ❌ JSON muito grande (inclui todos os episódios)
- ❌ Dados desnecessários para listar séries
- ❌ Performance ruim (carrega relacionamentos)
- ❌ Risco de loop infinito (se não tiver @JsonIgnore)

---

### ✅ AGORA (retorna SerieDTO - sem episódios)

```json
{
  "id": 1,
  "titulo": "Breaking Bad",
  "totalTemporadas": 5,
  "avaliacao": 9.5,
  "genero": "DRAMA",
  "atores": "Bryan Cranston, Aaron Paul",
  "poster": "https://...",
  "sinopse": "Um professor de química..."
}
```

**Vantagens:**
- ✅ JSON compacto (sem episódios)
- ✅ Apenas dados necessários
- ✅ Performance melhor
- ✅ Sem risco de loop infinito
- ✅ Controle total sobre o que é exposto

---

## 📊 Fluxo de Conversão

```
1. Banco de Dados (PostgreSQL)
   ↓
2. repositorio.findAll()
   ↓
3. List<Serie> (entidades JPA com episódios)
   ↓
4. .stream()
   ↓
5. .map(s -> new SerieDTO(...))
   ↓ (converte cada Serie em SerieDTO)
6. List<SerieDTO> (sem episódios)
   ↓
7. Spring converte para JSON
   ↓
8. Cliente recebe JSON limpo
```

---

## 🎯 Quando Usar DTO?

| Situação | Usar DTO? | Por quê? |
|----------|-----------|----------|
| Listar séries | ✅ SIM | Não precisa de episódios |
| Buscar série por ID | ⚠️ DEPENDE | Se quiser episódios, use entidade |
| Criar série | ✅ SIM | Validar apenas campos necessários |
| Atualizar série | ✅ SIM | Controlar campos editáveis |
| Relatórios | ✅ SIM | Dados específicos do relatório |

---

## 🐛 Possíveis Erros

### Erro 1: "Cannot find symbol: SerieDTO"
**Solução:** Recompile o projeto
```bash
mvn clean compile
```

### Erro 2: JSON vazio `[]`
**Causa:** Banco de dados vazio
**Solução:** Execute a versão console para buscar séries da API OMDB

### Erro 3: "No converter found for return value"
**Causa:** Jackson não consegue serializar
**Solução:** Verifique se SerieDTO é um record público

---

## ✅ Checklist de Teste

- [ ] Aplicação iniciou sem erros
- [ ] Navegador retorna JSON (não erro)
- [ ] JSON contém array de objetos
- [ ] Cada objeto tem 8 campos (id, titulo, totalTemporadas, avaliacao, genero, atores, poster, sinopse)
- [ ] Campo `episodios` NÃO aparece
- [ ] Status HTTP é 200 OK
- [ ] Content-Type é application/json

---

## 📝 Próximos Passos

- [ ] Criar endpoint para buscar série por ID (com episódios)
- [ ] Criar endpoint para top 5 séries
- [ ] Criar endpoint para buscar por categoria
- [ ] Adicionar paginação
- [ ] Adicionar filtros

---

**Desenvolvido por:** Guilherme Falcão  
**Curso:** Alura - Formação Avançando com Java  
**Aula:** 04 - Desenvolvimento Web (DTO)
