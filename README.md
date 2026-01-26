
![Programação-Formação Java](https://github.com/iasminaraujoc/3355-java-screenmatch-com-jpa/assets/84939115/3c51e000-962d-4dc9-97fc-1d384e2511a2)

# Java: persistência de dados e consultas com Spring Data JPA

Projeto desenvolvido no segundo curso da formação Avançando com Java da Alura

## 🔨 Objetivos do projeto

- Evoluir no projeto Screenmatch, iniciado no primeiro curso da formação, criando um menu com várias opções
- Modelar as abstrações da aplicação através de classes, enums, atributos e métodos
- Consumir a API do ChatGPT (alternativa: MyMemory API para tradução)
- Utilizar o Spring Data JPA para persistir dados no banco
- Conhecer vários tipos de banco de dados e utilizar o PostgreSQL
- Trabalhar com vários tipos de consultas ao banco de dados
- Aprofundar na interface JPARepository

## ✨ Funcionalidades Implementadas

### Aula 01 - Modelando a aplicação

- ✅ Menu interativo com loop para buscar múltiplas séries
- ✅ Classe `Serie` para modelar dados de séries de TV
- ✅ Enum `Categoria` para representar gêneros de forma tipada
- ✅ Conversão de dados da API OMDB para objetos Java
- ✅ Uso de `OptionalDouble` para tratamento seguro de conversões
- ✅ Integração com API MyMemory para tradução automática de sinopses
- ✅ Encapsulamento com métodos privados
- ✅ Mapeamento de atributos com `@JsonAlias`
- ✅ 8 exercícios resolvidos sobre manipulação de dados e enums

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.1.1
- Jackson (para processamento JSON)
- Maven
- API OMDB (busca de séries)
- API MyMemory (tradução gratuita)

## 📦 Estrutura do Projeto

```
src/main/java/br/com/alura/screenmatch/
├── model/
│   ├── Categoria.java (enum de gêneros)
│   ├── DadosSerie.java (record para API)
│   ├── DadosTemporada.java
│   ├── Episodio.java
│   └── Serie.java (classe principal)
├── service/
│   ├── ConsumoApi.java
│   ├── ConverteDados.java
│   ├── ConsultaChatGPT.java
│   └── traducao/
│       ├── ConsultaMyMemory.java
│       ├── DadosTraducao.java
│       └── DadosResposta.java
├── exercicios/
│   ├── ExerciciosResolvidos.java
│   ├── Mes.java (enum)
│   ├── Moeda.java (enum)
│   └── CodigoErro.java (enum)
└── principal/
    └── Principal.java (menu)
```

## 🚀 Como Executar

1. Clone o repositório
2. Abra o projeto no VS Code ou IntelliJ
3. Execute a classe `ScreenmatchApplication`
4. Navegue pelo menu:
   - **1** - Buscar séries na API OMDB
   - **2** - Buscar episódios de uma série
   - **3** - Listar séries buscadas (com tradução)
   - **4** - Ver exercícios resolvidos
   - **0** - Sair

## 📝 Conceitos Aprendidos

- Modelagem de classes e enums
- Conversão de tipos com Optional
- Consumo de APIs REST
- Processamento de JSON com Jackson
- Encapsulamento e boas práticas
- Streams e manipulação de coleções
- Tratamento de erros e exceções

## 🔗 Links Úteis

- [API OMDB](http://www.omdbapi.com/)
- [API MyMemory](https://mymemory.translated.net/)
- [Documentação Spring Boot](https://spring.io/projects/spring-boot)

---

**Desenvolvido por:** Guilherme Falcão  
**Curso:** Alura - Formação Avançando com Java
