# 🔒 CHECKLIST: Trocar Credenciais Expostas

## ⚠️ Suas credenciais estão no histórico do Git!

Mesmo que você tenha removido do código atual, elas ainda estão nos commits antigos.
Qualquer pessoa com acesso ao repositório pode ver o histórico.

---

## ✅ AÇÃO IMEDIATA: Trocar todas as credenciais

### 1. 🔑 API Key da OMDB (EXPOSTA: 6585022c)

**Status**: ⚠️ EXPOSTA no commit `a02cf83`

**Ação**:
- [ ] Acessar: http://www.omdbapi.com/apikey.aspx
- [ ] Gerar uma NOVA API key
- [ ] Atualizar no arquivo `.env`:
  ```
  OMDB_API_KEY=sua-nova-chave-aqui
  ```
- [ ] (Opcional) Desativar a chave antiga no painel da OMDB

---

### 2. 🔐 Senha do PostgreSQL (EXPOSTA: 1234)

**Status**: ⚠️ EXPOSTA no commit `a02cf83`

**Ação**:
- [ ] Conectar no PostgreSQL (DBeaver ou pgAdmin)
- [ ] Executar comando SQL:
  ```sql
  ALTER USER postgres WITH PASSWORD 'nova_senha_segura_123';
  ```
- [ ] Atualizar no arquivo `.env`:
  ```
  DB_PASSWORD=nova_senha_segura_123
  ```

---

### 3. 👤 Usuário do PostgreSQL (EXPOSTO: postgres)

**Status**: ⚠️ EXPOSTO no commit `a02cf83`

**Ação** (OPCIONAL - se quiser mais segurança):
- [ ] Criar novo usuário no PostgreSQL:
  ```sql
  CREATE USER screenmatch_user WITH PASSWORD 'senha_forte_456';
  GRANT ALL PRIVILEGES ON DATABASE alura_series TO screenmatch_user;
  ```
- [ ] Atualizar no arquivo `.env`:
  ```
  DB_USERNAME=screenmatch_user
  DB_PASSWORD=senha_forte_456
  ```

---

## 📝 Após trocar as credenciais:

- [ ] Testar se a aplicação ainda funciona
- [ ] Fazer commit das mudanças de segurança:
  ```bash
  git add .gitignore .env.example application.properties Principal.java SEGURANCA.md
  git commit -m "🔒 Segurança: Migrar credenciais para variáveis de ambiente"
  git push
  ```
- [ ] Adicionar aviso no README sobre credenciais antigas:
  ```markdown
  ## ⚠️ Aviso de Segurança
  Credenciais antigas foram expostas em commits anteriores e foram TROCADAS.
  As credenciais nos commits antigos NÃO funcionam mais.
  ```

---

## 🎯 Qual opção escolher?

| Situação | Recomendação |
|----------|--------------|
| Repositório privado (só você tem acesso) | ✅ Trocar credenciais (OPÇÃO 2) |
| Repositório público no GitHub | ⚠️ Limpar histórico + Trocar credenciais |
| Repositório compartilhado com equipe | ⚠️ Limpar histórico + Trocar credenciais |
| Credenciais de produção expostas | 🚨 URGENTE: Trocar IMEDIATAMENTE |

---

## 📚 Referências

- [GitHub: Removing sensitive data](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/removing-sensitive-data-from-a-repository)
- [BFG Repo-Cleaner](https://rtyley.github.io/bfg-repo-cleaner/)
- [Git filter-branch](https://git-scm.com/docs/git-filter-branch)

---

**🔒 Lembre-se**: Trocar as credenciais é OBRIGATÓRIO se elas foram expostas!
