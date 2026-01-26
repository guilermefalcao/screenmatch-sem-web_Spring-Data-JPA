# 🔒 Guia de Segurança - Variáveis de Ambiente

## ⚠️ Por que usar variáveis de ambiente?

Credenciais como **senhas**, **API keys** e **tokens** NUNCA devem estar no código-fonte porque:

1. ❌ **Vazamento no Git**: Qualquer pessoa com acesso ao repositório vê suas senhas
2. ❌ **Histórico permanente**: Mesmo deletando depois, fica no histórico do Git
3. ❌ **Segurança**: Hackers buscam credenciais em repositórios públicos
4. ✅ **Boas práticas**: Separar configuração de código é padrão da indústria

---

## 📋 Passo a Passo - Configuração

### 1️⃣ Copiar o arquivo de exemplo

```bash
# Windows (CMD)
copy .env.example .env

# Windows (PowerShell)
Copy-Item .env.example .env

# Linux/Mac
cp .env.example .env
```

### 2️⃣ Editar o arquivo .env com suas credenciais REAIS

Abra o arquivo `.env` e preencha:

```properties
# Sua chave da API OMDB (obtenha em http://www.omdbapi.com/apikey.aspx)
OMDB_API_KEY=6585022c

# Sua chave da OpenAI (obtenha em https://platform.openai.com/api-keys)
OPENAI_API_KEY=sk-proj-xxxxxxxxxxxxx

# Dados do seu banco PostgreSQL
DB_URL=jdbc:postgresql://localhost:5433/alura_series
DB_USERNAME=postgres
DB_PASSWORD=1234
```

### 3️⃣ Verificar se o .env está no .gitignore

O arquivo `.gitignore` já contém:

```
# ⚠️ ARQUIVOS SENSÍVEIS (SEGURANÇA)
.env
.env.local
.env.*.local
```

✅ Isso garante que o `.env` NUNCA será enviado ao Git!

### 4️⃣ Executar a aplicação

O Spring Boot automaticamente lê as variáveis do `.env` e substitui no `application.properties`:

```properties
# application.properties usa ${VARIAVEL:valor_padrao}
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5433/alura_series}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:1234}
```

---

## 🔍 Como funciona?

### No application.properties:

```properties
# Sintaxe: ${NOME_VARIAVEL:valor_se_nao_existir}
spring.datasource.password=${DB_PASSWORD:1234}
```

- **${DB_PASSWORD}**: Busca a variável de ambiente `DB_PASSWORD`
- **:1234**: Se não encontrar, usa `1234` como padrão

### No código Java:

```java
// Lê a variável de ambiente OMDB_API_KEY
private final String API_KEY = "&apikey=" + System.getenv("OMDB_API_KEY");
```

---

## 📦 O que vai para cada arquivo?

| Arquivo | Vai pro Git? | Conteúdo |
|---------|--------------|----------|
| `.env` | ❌ NÃO | Credenciais REAIS (senhas, keys) |
| `.env.example` | ✅ SIM | Template com placeholders |
| `.gitignore` | ✅ SIM | Lista de arquivos ignorados |
| `application.properties` | ✅ SIM | Referências às variáveis `${VAR}` |
| `Principal.java` | ✅ SIM | Código usando `System.getenv()` |

---

## 🛡️ Outras informações sensíveis que devem ir para o .env:

### ✅ Sempre proteger:

- 🔑 **API Keys** (OMDB, OpenAI, AWS, Google, etc.)
- 🔐 **Senhas de banco de dados**
- 🎫 **Tokens de autenticação** (JWT secrets, OAuth tokens)
- 📧 **Credenciais de email** (SMTP user/password)
- ☁️ **Chaves de cloud** (AWS Access Key, Azure credentials)
- 🔒 **Chaves de criptografia**
- 🌐 **URLs com tokens** (webhooks com secrets)

### ❌ Não precisa proteger:

- 📝 URLs públicas de APIs (sem tokens)
- 🔢 Portas padrão (8080, 5432)
- 📂 Nomes de bancos de dados (se não forem sensíveis)
- ⚙️ Configurações de framework (Hibernate ddl-auto, show-sql)

---

## 🚨 E se eu já commitei credenciais?

### Solução 1: Remover do histórico (AVANÇADO)

```bash
# Remove arquivo do histórico do Git
git filter-branch --force --index-filter \
  "git rm --cached --ignore-unmatch .env" \
  --prune-empty --tag-name-filter cat -- --all

# Força o push (CUIDADO!)
git push origin --force --all
```

### Solução 2: Trocar as credenciais

1. ✅ **Gerar novas API keys** nos serviços (OMDB, OpenAI)
2. ✅ **Trocar senha do banco de dados**
3. ✅ **Atualizar o .env** com as novas credenciais
4. ✅ **Adicionar .env no .gitignore** (se ainda não estiver)

---

## ✅ Checklist de Segurança

Antes de fazer commit, verifique:

- [ ] Arquivo `.env` está no `.gitignore`
- [ ] Não há senhas hardcoded no código
- [ ] Não há API keys hardcoded no código
- [ ] `application.properties` usa `${VARIAVEL}` em vez de valores diretos
- [ ] Arquivo `.env.example` está atualizado (sem credenciais reais)
- [ ] Executei `git status` e o `.env` NÃO aparece

---

## 📚 Referências

- [The Twelve-Factor App - Config](https://12factor.net/config)
- [Spring Boot - External Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config)
- [OWASP - Secrets Management](https://cheatsheetseries.owasp.org/cheatsheets/Secrets_Management_Cheat_Sheet.html)

---

**🎯 Lembre-se**: Segurança não é opcional, é obrigatória! 🔒
