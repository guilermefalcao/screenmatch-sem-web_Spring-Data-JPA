# 🧪 Guia de Teste - Opção 10: Top 5 Episódios por Série

## 🎯 O que faz:

Busca os **5 episódios com melhor avaliação** de uma série específica usando JPQL com JOIN, ORDER BY e LIMIT.

---

## 📋 Pré-requisitos:

1. ✅ Série salva no banco (opção 1)
2. ✅ Episódios salvos no banco (opção 2)

---

## 🧪 Como testar:

### Cenário 1: Buscar série primeiro (opção 4) e depois top 5 episódios (opção 10)

```
1. Execute a aplicação
2. Digite: 4 (Buscar série por título)
3. Digite: The Boys
   → Exibe: Dados da série: Serie{id=1, titulo='The Boys', ...}
4. Digite: 10 (Top 5 episódios por série)
   → Sistema reutiliza a série buscada anteriormente
   → Exibe os 5 melhores episódios de The Boys
```

**Saída esperada:**
```
🏆 Top 5 Episódios de The Boys:
Série: The Boys | Temporada: 2 | Episódio: 3 - Over the Hill with the Swords of a Thousand Men | Avaliação: 9.0
Série: The Boys | Temporada: 4 | Episódio: 4 - Wisdom of the Ages | Avaliação: 8.9
Série: The Boys | Temporada: 2 | Episódio: 6 - The Bloody Doors Off | Avaliação: 8.9
Série: The Boys | Temporada: 1 | Episódio: 1 - The Name of the Game | Avaliação: 8.7
Série: The Boys | Temporada: 1 | Episódio: 4 - The Female of the Species | Avaliação: 8.7
```

---

### Cenário 2: Ir direto para opção 10 (sem buscar série antes)

```
1. Execute a aplicação
2. Digite: 10 (Top 5 episódios por série)
   → Sistema detecta que não há série buscada
   → Solicita: "Escolha uma serie pelo nome:"
3. Digite: Gilmore Girls
   → Busca a série no banco
   → Exibe os 5 melhores episódios de Gilmore Girls
```

**Saída esperada:**
```
Escolha uma serie pelo nome: 
Gilmore Girls
Dados da série: Serie{id=2, titulo='Gilmore Girls', ...}

🏆 Top 5 Episódios de Gilmore Girls:
Série: Gilmore Girls | Temporada: 3 | Episódio: 7 - They Shoot Gilmores, Don't They? | Avaliação: 8.9
Série: Gilmore Girls | Temporada: 6 | Episódio: 9 - The Prodigal Daughter Returns | Avaliação: 8.4
Série: Gilmore Girls | Temporada: 2 | Episódio: 16 - There's the Rub | Avaliação: 8.4
Série: Gilmore Girls | Temporada: 3 | Episódio: 16 - The Big One | Avaliação: 8.4
Série: Gilmore Girls | Temporada: 2 | Episódio: 10 - The Bracebridge Dinner | Avaliação: 8.6
```

---

## ⚠️ Possíveis erros:

### Erro 1: "Nenhum episódio encontrado"
```
❌ Nenhum episódio encontrado para a série: The Boys
⚠️  Certifique-se de que os episódios foram salvos (opção 2).
```

**Causa:** Série existe no banco, mas não tem episódios salvos  
**Solução:** Use opção 2 para buscar e salvar os episódios

---

### Erro 2: "Série não encontrada"
```
❌ Série não encontrada!
```

**Causa:** Série não existe no banco  
**Solução:** Use opção 1 para buscar e salvar a série na API OMDB

---

## 🔍 Verificar no DBeaver:

### Query 1: Ver top 5 episódios de uma série específica
```sql
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

### Query 2: Ver todas as avaliações dos episódios de uma série
```sql
SELECT 
    e.temporada,
    e.numero_episodio,
    e.titulo,
    e.avaliacao
FROM episodios e
JOIN series s ON e.serie_id = s.id
WHERE s.titulo = 'The Boys'
ORDER BY e.avaliacao DESC;
```

### Query 3: Contar episódios por série
```sql
SELECT 
    s.titulo,
    COUNT(e.id) AS total_episodios,
    AVG(e.avaliacao) AS media_avaliacao,
    MAX(e.avaliacao) AS melhor_avaliacao
FROM series s
LEFT JOIN episodios e ON s.id = e.serie_id
GROUP BY s.titulo
ORDER BY media_avaliacao DESC;
```

---

## 🎯 Fluxo completo de teste:

```
1. Opção 1: Buscar "The Boys" (salva série no banco)
   ↓
2. Opção 2: Buscar episódios de "The Boys" (salva 32 episódios)
   ↓
3. Opção 4: Buscar série "The Boys" (armazena em serieBusca)
   ↓
4. Opção 10: Top 5 episódios (reutiliza serieBusca)
   → Exibe os 5 melhores episódios
   ↓
5. DBeaver: SELECT * FROM episodios WHERE serie_id = 1 ORDER BY avaliacao DESC LIMIT 5
   → Confirma os mesmos 5 episódios
```

---

## 📊 Comparação: Opção 6 vs Opção 10

| Aspecto | Opção 6 (Top 5 Séries) | Opção 10 (Top 5 Episódios) |
|---------|------------------------|----------------------------|
| **O que busca** | Séries | Episódios |
| **Critério** | Avaliação da série | Avaliação do episódio |
| **Escopo** | Todas as séries | Uma série específica |
| **Parâmetro** | Nenhum | Série (objeto) |
| **SQL** | ORDER BY avaliacao DESC LIMIT 5 | WHERE s.id = ? ORDER BY e.avaliacao DESC LIMIT 5 |
| **Uso** | Ranking geral | Melhores episódios de uma série |

---

## 💡 Conceitos aprendidos:

1. **Reutilização de variáveis de instância** (`serieBusca`)
2. **JPQL com WHERE usando objeto** (`WHERE s = :serie`)
3. **ORDER BY + LIMIT** para top N
4. **JOIN entre entidades relacionadas**
5. **Formatação com printf** para saída elegante

---

**Pronto para testar!** 🚀

Execute `mvn spring-boot:run` e teste a opção 10!
