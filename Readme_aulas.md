# 📚 Roteiro do Curso - Spring Data JPA

Guia passo a passo de tudo que foi implementado no curso.

---

## 🎯 AULA 01 - Modelando a Aplicação

### 1. Criação da Classe Serie
**Arquivo:** `model/Serie.java`

**O que faz:** Representa uma série de TV como um objeto Java

**Passos:**
1. Criar classe com atributos: titulo, totalTemporadas, avaliacao, genero, atores, poster, sinopse
2. Criar construtor que recebe `DadosSerie` (dados da API)
3. Converter avaliação usando `OptionalDouble` para tratar erros
4. Criar getters e setters para todos os atributos

**Conceitos aprendidos:**
- Modelagem de classes
- Conversão de tipos com Optional
- Encapsulamento

---

### 2. Criação do Enum Categoria
**Arquivo:** `model/Categoria.java`

**O que faz:** Define os gêneros de séries de forma tipada e segura

**Passos:**
1. Criar enum com valores: ACAO, ROMANCE, COMEDIA, DRAMA, CRIME, etc.
2. Adicionar atributos: categoriaOmdb (inglês) e categoriaPortugues
3. Criar método `fromString()` para converter String da API em enum
4. Criar getters para acessar os valores

**Conceitos aprendidos:**
- Enums com atributos
- Métodos em enums
- Conversão de String para enum

---

### 3. Integração com API de Tradução
**Arquivos:** `service/traducao/ConsultaMyMemory.java`, `DadosTraducao.java`, `DadosResposta.java`

**O que faz:** Traduz sinopses do inglês para português automaticamente

**Passos:**
1. Criar records `DadosTraducao` e `DadosResposta` para mapear JSON da API
2. Criar classe `ConsultaMyMemory` com método `obterTraducao()`
3. Usar `URLEncoder` para codificar o texto
4. Consumir API MyMemory (gratuita, 5000 caracteres/dia)
5. Processar resposta JSON com Jackson

**Conceitos aprendidos:**
- Consumo de APIs REST
- Processamento de JSON
- Records para DTOs

---

### 4. Menu Interativo
**Arquivo:** `principal/Principal.java`

**O que faz:** Menu com loop para buscar múltiplas séries

**Passos:**
1. Criar loop `while` que roda até usuário escolher sair
2. Usar `switch-case` para navegar entre opções
3. Métodos privados para cada funcionalidade (encapsulamento)
4. Scanner para ler entrada do usuário

**Conceitos aprendidos:**
- Loops e controle de fluxo
- Encapsulamento com métodos privados
- Interação com usuário

---

### 5. Exercícios Resolvidos
**Arquivos:** `exercicios/ExerciciosResolvidos.java`, `Mes.java`, `Moeda.java`, `CodigoErro.java`

**O que faz:** 8 exercícios sobre manipulação de dados e enums

**Exercícios:**
1. Converter lista de strings para números (ignorando inválidos)
2. Processar número em Optional
3. Obter primeiro e último nome
4. Verificar palíndromo
5. Converter emails para minúsculas
6. Enum Mes com dias do mês
7. Enum Moeda com conversão
8. Enum CodigoErro HTTP

**Conceitos aprendidos:**
- Streams e lambdas
- Optional
- Manipulação de Strings
- Enums avançados

---

## 🗄️ AULA 02 - Persistência de Dados com JPA

### 1. Configuração do Banco de Dados
**Arquivo:** `src/main/resources/application.properties`

**O que faz:** Configura conexão com PostgreSQL

**Passos:**
1. Adicionar dependências no `pom.xml`:
   - `spring-boot-starter-data-jpa`
   - `postgresql` (driver)
2. Criar arquivo `application.properties`
3. Configurar URL, usuário, senha e porta do banco
4. Configurar Hibernate (ddl-auto, show-sql, dialect)

**Configurações importantes:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/alura_series
spring.datasource.username=postgres
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update  # Cria/atualiza tabelas automaticamente
spring.jpa.show-sql=true              # Mostra SQL no console
```

**Conceitos aprendidos:**
- Configuração de DataSource
- Hibernate DDL (create, update, validate)
- Dialetos SQL

---

### 2. Transformar Serie em Entidade JPA
**Arquivo:** `model/Serie.java`

**O que faz:** Mapeia a classe Serie para uma tabela no banco

**Passos:**
1. Adicionar anotação `@Entity` na classe
2. Adicionar `@Table(name = "series")` para definir nome da tabela
3. Criar campo `id` com anotações:
   - `@Id` - Define como chave primária
   - `@GeneratedValue(strategy = GenerationType.IDENTITY)` - Auto-increment
4. Adicionar `@Column(unique = true)` no titulo
5. Adicionar `@Enumerated(EnumType.STRING)` no genero
6. Adicionar `@Transient` na lista de episódios (não persiste no banco)
7. Criar construtor padrão vazio (obrigatório para JPA)
8. Criar getters e setters para id e episodios

**Anotações JPA:**
- `@Entity` - Marca como entidade JPA
- `@Table` - Define nome da tabela
- `@Id` - Chave primária
- `@GeneratedValue` - Valor gerado automaticamente
- `@Column` - Configurações da coluna
- `@Enumerated` - Como salvar enum (STRING ou ORDINAL)
- `@Transient` - Campo não persistido

**Conceitos aprendidos:**
- Mapeamento objeto-relacional (ORM)
- Anotações JPA
- Estratégias de geração de ID

---

### 3. Criar Repository
**Arquivo:** `repository/SerieRepository.java`

**O que faz:** Interface para operações de banco de dados

**Passos:**
1. Criar interface que estende `JpaRepository<Serie, Long>`
2. Não precisa implementar nada! Spring cria automaticamente

**Métodos disponíveis automaticamente:**
- `save(serie)` - Salva ou atualiza
- `findById(id)` - Busca por ID
- `findAll()` - Lista todas
- `delete(serie)` - Remove
- `count()` - Conta registros

**Conceitos aprendidos:**
- Spring Data JPA
- Repositories
- Métodos CRUD automáticos

---

### 4. Injeção de Dependência
**Arquivos:** `ScreenmatchApplication.java` e `Principal.java`

**O que faz:** Conecta o repositório com a aplicação

**Passos em ScreenmatchApplication:**
1. Adicionar `@Autowired` no repositório
2. Passar repositório para Principal no método `run()`

**Passos em Principal:**
1. Criar atributo `SerieRepository repositorio`
2. Criar construtor que recebe o repositório
3. Usar `repositorio.save(serie)` para salvar no banco

**Conceitos aprendidos:**
- Injeção de dependência
- @Autowired
- Inversão de controle (IoC)

---

### 5. Salvar Série no Banco
**Arquivo:** `principal/Principal.java` - método `buscarSerieWeb()`

**O que faz:** Busca série na API e salva no banco

**Fluxo:**
1. Usuário digita nome da série
2. Busca dados na API OMDB
3. Converte `DadosSerie` para `Serie` (entidade)
4. Chama `repositorio.save(serie)` - salva no banco
5. Hibernate executa INSERT automaticamente

**SQL gerado automaticamente:**
```sql
INSERT INTO series (titulo, total_temporadas, avaliacao, genero, atores, poster, sinopse)
VALUES ('Friends', 10, 8.9, 'COMEDIA', 'Jennifer Aniston...', 'https://...', 'A vida...');
```

**Conceitos aprendidos:**
- Persistência de dados
- ORM em ação
- SQL gerado automaticamente

---

### 6. Verificar Dados no Banco
**Ferramenta:** DBeaver ou pgAdmin

**Comandos SQL:**
```sql
-- Ver todas as tabelas
SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';

-- Ver estrutura da tabela
SELECT column_name, data_type FROM information_schema.columns WHERE table_name = 'series';

-- Ver dados salvos
SELECT * FROM series;

-- Buscar por gênero
SELECT titulo, genero, avaliacao FROM series WHERE genero = 'COMEDIA';

-- Contar séries
SELECT COUNT(*) FROM series;
```

**Conceitos aprendidos:**
- Consultas SQL básicas
- Verificação de dados
- Estrutura de tabelas

---

## 📊 Estrutura do Banco de Dados

### Tabela: series

| Coluna | Tipo | Restrições |
|--------|------|------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| titulo | VARCHAR | UNIQUE, NOT NULL |
| total_temporadas | INTEGER | |
| avaliacao | DOUBLE | |
| genero | VARCHAR | (salva como texto: "ACAO", "COMEDIA") |
| atores | VARCHAR | |
| poster | VARCHAR | |
| sinopse | TEXT | |

---

## 🔄 Fluxo Completo da Aplicação

```
1. Usuário escolhe opção 1 (Buscar séries)
   ↓
2. Digite nome da série
   ↓
3. ConsumoApi busca na API OMDB
   ↓
4. ConverteDados converte JSON para DadosSerie
   ↓
5. ConsultaMyMemory traduz sinopse
   ↓
6. Cria objeto Serie (entidade JPA)
   ↓
7. repositorio.save(serie) salva no banco
   ↓
8. Hibernate gera e executa SQL INSERT
   ↓
9. Dados salvos no PostgreSQL
   ↓
10. Pode consultar no DBeaver: SELECT * FROM series;
```

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.1.1**
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados relacional
- **Hibernate** - ORM (implementação do JPA)
- **Jackson** - Processamento JSON
- **Maven** - Gerenciamento de dependências
- **API OMDB** - Busca de séries
- **API MyMemory** - Tradução gratuita

---

## 📝 Próximas Aulas

- [ ] Consultas personalizadas com JPQL
- [ ] Relacionamentos entre entidades
- [ ] Derived Query Methods
- [ ] Paginação e ordenação
- [ ] Queries nativas

---

**Desenvolvido por:** Guilherme Falcão  
**Curso:** Alura - Formação Avançando com Java  
**Última atualização:** Aula 02 - Persistência de Dados
