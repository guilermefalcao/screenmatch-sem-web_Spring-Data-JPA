# ========================================
# SCRIPT PARA LIMPAR CREDENCIAIS DO GIT
# ========================================
# ⚠️ ATENÇÃO: Este script reescreve o histórico do Git
# ⚠️ Use apenas se o repositório ainda não foi compartilhado com outras pessoas
# ========================================

# PASSO 1: Fazer backup do repositório
echo "📦 Fazendo backup do repositório..."
cd ..
xcopy "3355-java-screenmatch-com-jpa" "3355-java-screenmatch-com-jpa-BACKUP" /E /I /H /Y
cd "3355-java-screenmatch-com-jpa"

# PASSO 2: Instalar BFG Repo-Cleaner (ferramenta para limpar histórico)
# Baixe em: https://rtyley.github.io/bfg-repo-cleaner/
# Ou use o comando abaixo se tiver Java instalado:
# java -jar bfg.jar --replace-text passwords.txt

# PASSO 3: Criar arquivo com as credenciais a serem removidas
echo 6585022c==>OMDB_API_KEY_REMOVIDA> passwords.txt
echo 1234==>SENHA_REMOVIDA>> passwords.txt
echo postgres==>USUARIO_REMOVIDO>> passwords.txt

# PASSO 4: Executar BFG para limpar o histórico
# java -jar bfg.jar --replace-text passwords.txt .git

# PASSO 5: Limpar referências antigas
git reflog expire --expire=now --all
git gc --prune=now --aggressive

# PASSO 6: Forçar push (CUIDADO!)
# git push origin --force --all
# git push origin --force --tags

echo "✅ Histórico limpo! Verifique com: git log --all --full-history -S '6585022c'"
