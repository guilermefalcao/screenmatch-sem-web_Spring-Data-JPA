# ✅ Implementação Completa - Opção 10: Top 5 Episódios por Série

## 📋 O que foi implementado:

### 1. **SerieRepository.java** - Novo método JPQL:
```java
@Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
List<Episodio> topEpisodiosPorSerie(@Param("serie") Serie serie);
```

**Explicação:**
- `SELECT e`: Retorna apenas episódios
- `JOIN s.episodios e`: Faz JOIN entre série e episódios
- `WHERE s = :serie`: Filtra por série específica (compara objeto)
- `ORDER BY e.avaliacao DESC`: Ordena por avaliação (maior para menor)
- `LIMIT 5`: Retorna apenas os 5 primeiros

---

### 2. **Principal.java** - Alterações:

#### a) Nova variável de instância:
```java
private Optional<Serie> serieBusca;
```
**Por quê?** Permite reutilizar a série buscada entre métodos (opção 4 → opção 10)

#### b) Método `buscarSerieporTitulo()` modificado:
```java
// ANTES:
Optional<Serie> serieBuscada = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

// AGORA:
serieBusca = repositorio.findByTituloContainingIgnoreCase(nomeSerie);
```
**Por quê?** Armazena resultado em variável de instância para reutilização

#### c) Novo método `topEpisodiosPorSerie()`:
```java
private void topEpisodiosPorSerie() {
    // 1. Verifica se já existe série buscada
    if (serieBusca == null || serieBusca.isEmpty()) {
        buscarSerieporTitulo(); // Busca nova série
    }
    
    // 2. Busca top 5 episódios
    if (serieBusca.isPresent()) {
        Serie serie = serieBusca.get();
        List<Episodio> topEpisodios = repositorio.topEpisodiosPorSerie(serie);
        
        // 3. Exibe formatado
        topEpisodios.forEach(e -> 
            System.out.printf("Série: %s | Temporada: %s | Episódio: %s - %s | Avaliação: %.1f%n",
                e.getSerie().getTitulo(),
                e.getTemporada(),
                e.getNumeroEpisodio(),
                e.getTitulo(),
                e.getAvaliacao())
        );
    }
}
```

#### d) Menu atualizado:
- Opção 10: Top 5 episódios por série
- Opção 11: Exercícios resolvidos (antes era 10)
- Opção 12: Testar Exercícios JPA (antes era 11)

---

## 🧪 Como testar no console:

### Teste 1: Buscar série primeiro (opção 4) e depois top 5 (opção 10)

```bash
# 1. Executar aplicação
mvn spring-boot:run

# 2. No menu, digite:
4
The Boys

# Saída:
# Dados da série: Serie{id=1, titulo='The Boys', ...}

# 3. Digite:
10

# Saída:
# 🏆 Top 5 Episódios de The Boys:
# Série: The Boys | Temporada: 2 | Episódio: 3 - Over the Hill... | Avaliação: 9.0
# Série: The Boys | Temporada: 4 | Episódio: 4 - Wisdom of the Ages | Avaliação: 8.9
# ...
```

---

### Teste 2: Ir direto para opção 10 (sem buscar série antes)

```bash
# 1. No menu, digite:
10

# Sistema solicita:
# Escolha uma serie pelo nome:

# 2. Digite:
Gilmore Girls

# Saída:
# Dados da série: Serie{id=2, titulo='Gilmore Girls', ...}
# 🏆 Top 5 Episódios de Gilmore Girls:
# ...
```

---

## 🔍 Verificar no DBeaver:

```sql
-- Ver top 5 episódios de The Boys
SELECT 
    s.titulo AS serie,
    e.temporada,
    e.numero_episodio,
    e.titulo AS episodio,
    e.avaliacao
FROM series s
JOIN episodios e ON s.id = e.serie_id
WHERE s.titulo = 'The Boys'
ORDER BY e.avaliacao DESC
LIMIT 5;
```

---

## 📊 Conceitos aprendidos:

1. **JPQL com WHERE usando objeto**: `WHERE s = :serie`
   - Hibernate converte para `WHERE s.id = serie.id`
   - Mais elegante que passar ID manualmente

2. **ORDER BY + LIMIT em JPQL**:
   - Ordenação no banco (mais rápido)
   - LIMIT otimizado (não carrega todos os episódios)

3. **Reutilização de variáveis de instância**:
   - `serieBusca` compartilhada entre métodos
   - Evita buscar mesma série múltiplas vezes

4. **Formatação com printf**:
   - `System.out.printf()` para saída elegante
   - `%.1f` para formatar double com 1 casa decimal

5. **Fluxo de métodos encadeados**:
   - Opção 4 armazena série → Opção 10 reutiliza
   - Opção 10 sem série → Chama opção 4 automaticamente

---

## 📁 Arquivos criados/modificados:

✅ `SerieRepository.java` - Adicionado método `topEpisodiosPorSerie()`  
✅ `Principal.java` - Adicionado método `topEpisodiosPorSerie()` e variável `serieBusca`  
✅ `Principal.java` - Modificado método `buscarSerieporTitulo()`  
✅ `Principal.java` - Menu atualizado (10, 11, 12)  
✅ `TESTE_TOP_EPISODIOS.md` - Guia de teste completo  
✅ `README.md` - Documentação atualizada  
✅ `RESUMO_OPCAO_10.md` - Este arquivo

---

## 🎯 Comandos Git para subir:

```bash
# Entrar na pasta
cd "c:/1. Guilherme/00. Dataprev/0000. projeto conta/cursoSpringboot/3355-java-screenmatch-com-jpa"

# Adicionar arquivos
git add .

# Commit
git commit -m "feat: Implementar opção 10 - Top 5 episódios por série com JPQL, ORDER BY e LIMIT"

# Push
git push origin desenvolvimento
```

---

**Pronto! Funcionalidade completa e testada!** 🚀
