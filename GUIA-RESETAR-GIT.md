# 🔄 Guia: Resetar Git e Começar do Zero

## 🎯 Objetivo
Apagar TODO o histórico do Git (incluindo credenciais expostas) e criar um repositório limpo como se fosse o primeiro commit.

---

## ⚠️ ANTES DE COMEÇAR

### Verificar o que NÃO vai subir no Git:

```bash
# Ver o que está no .gitignore
type .gitignore

# Ver status (o .env NÃO deve aparecer)
git status
```

✅ **Confirme que o `.env` está no `.gitignore`**

---

## 🚀 PASSO A PASSO

### Opção 1: Usar o script automático (RECOMENDADO)

```bash
# Execute o script
RESETAR-GIT.bat
```

O script faz:
1. ✅ Backup da pasta `.git` (segurança)
2. ✅ Remove a pasta `.git` (apaga histórico)
3. ✅ Inicializa novo repositório
4. ✅ Adiciona arquivos (`.env` é ignorado automaticamente)
5. ✅ Cria primeiro commit limpo

---

### Opção 2: Comandos manuais

```bash
# 1. Fazer backup (segurança)
xcopy .git .git-backup /E /I /H /Y

# 2. Apagar pasta .git (APAGA TODO O HISTÓRICO)
rmdir /s /q .git

# 3. Inicializar novo repositório
git init

# 4. Adicionar todos os arquivos (exceto .env)
git add .

# 5. Ver o que será commitado (confirme que .env NÃO está na lista)
git status

# 6. Criar primeiro commit
git commit -m "🎉 Initial commit: Screenmatch com Spring Data JPA e segurança (variáveis de ambiente)"
```

---

## 📤 SUBIR PARA O GITHUB

### Se o repositório remoto JÁ EXISTE:

```bash
# 1. Conectar ao repositório remoto
git remote add origin https://github.com/guilermefalcao/screenmatch-sem-web_Spring-Data-JPA.git

# 2. Renomear branch para main (se necessário)
git branch -M main

# 3. Forçar push (SOBRESCREVE o histórico remoto)
git push -u origin main --force
```

### Se o repositório remoto NÃO EXISTE:

```bash
# 1. Criar repositório no GitHub (via navegador)
# https://github.com/new

# 2. Conectar e fazer push
git remote add origin https://github.com/guilermefalcao/SEU-REPO.git
git branch -M main
git push -u origin main
```

---

## ✅ VERIFICAÇÕES FINAIS

### 1. Confirmar que .env NÃO está no Git:

```bash
# Listar arquivos rastreados pelo Git
git ls-files | findstr ".env"
```

**Resultado esperado**: Nenhuma linha (vazio)

### 2. Confirmar que não há credenciais no histórico:

```bash
# Buscar por senha no histórico
git log --all --full-history -S "1234"

# Buscar por API key no histórico
git log --all --full-history -S "6585022c"
```

**Resultado esperado**: Nenhum commit encontrado

### 3. Ver o primeiro commit:

```bash
git log --oneline
```

**Resultado esperado**: Apenas 1 commit com a mensagem "Initial commit"

---

## 📋 CHECKLIST

Antes de fazer push, confirme:

- [ ] Arquivo `.env` existe localmente
- [ ] Arquivo `.env` está no `.gitignore`
- [ ] Comando `git status` NÃO mostra o `.env`
- [ ] Comando `git ls-files | findstr ".env"` retorna vazio
- [ ] Arquivo `.env.example` ESTÁ no Git (é o template público)
- [ ] Histórico foi resetado (apenas 1 commit)
- [ ] Nenhuma credencial no histórico

---

## 🔍 ESTRUTURA FINAL NO GIT

### ✅ Arquivos que SOBEM no Git:

```
✅ .gitignore (protege o .env)
✅ .env.example (template público)
✅ application.properties (com ${VARIAVEIS})
✅ Principal.java (com System.getenv())
✅ SEGURANCA.md (guia de segurança)
✅ README.md
✅ Readme_aulas.md
✅ pom.xml
✅ src/ (todo o código)
```

### ❌ Arquivos que NÃO SOBEM no Git:

```
❌ .env (credenciais reais)
❌ target/ (arquivos compilados)
❌ .idea/ (configurações da IDE)
❌ *.class (bytecode)
```

---

## 🎉 PRONTO!

Agora seu repositório está LIMPO:
- ✅ Sem credenciais no histórico
- ✅ Sem commits antigos
- ✅ Variáveis de ambiente protegidas
- ✅ .gitignore configurado
- ✅ Pronto para compartilhar com segurança

---

## 🆘 Se algo der errado:

```bash
# Restaurar backup
rmdir /s /q .git
xcopy .git-backup .git /E /I /H /Y
```

---

**🔒 Lembre-se**: Após resetar, o histórico antigo com credenciais será APAGADO permanentemente!
