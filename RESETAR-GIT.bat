@echo off
chcp 65001 >nul
echo ========================================
echo RESETAR REPOSITORIO GIT - INICIO LIMPO
echo ========================================
echo.
echo ⚠️  ATENCAO: Este script vai APAGAR TODO o historico do Git!
echo ⚠️  O repositorio sera recriado do ZERO, como se fosse o primeiro commit.
echo ⚠️  Credenciais antigas serao REMOVIDAS do historico.
echo.
echo Pressione qualquer tecla para continuar ou Ctrl+C para cancelar...
pause >nul

echo.
echo [1/7] Fazendo backup da pasta .git...
if exist ".git-backup" rmdir /s /q ".git-backup"
xcopy ".git" ".git-backup" /E /I /H /Y >nul 2>&1
echo ✅ Backup criado em .git-backup

echo.
echo [2/7] Removendo pasta .git (apagando historico)...
rmdir /s /q ".git"
echo ✅ Historico apagado

echo.
echo [3/7] Inicializando novo repositorio Git...
git init
echo ✅ Repositorio inicializado

echo.
echo [4/7] Verificando se .env esta protegido pelo .gitignore...
findstr /C:".env" .gitignore >nul
if %errorlevel%==0 (
    echo ✅ .env esta no .gitignore
) else (
    echo ❌ ERRO: .env NAO esta no .gitignore!
    pause
    exit /b 1
)

echo.
echo [5/7] Adicionando todos os arquivos (exceto .env)...
git add .
echo ✅ Arquivos adicionados

echo.
echo [6/7] Criando primeiro commit limpo...
git commit -m "🎉 Initial commit: Screenmatch com Spring Data JPA e segurança (variáveis de ambiente)"
echo ✅ Commit criado

echo.
echo [7/7] Conectando ao GitHub e fazendo push...
git remote add origin https://github.com/guilermefalcao/screenmatch-sem-web_Spring-Data-JPA.git
git branch -M main
echo.
echo ⚠️  Fazendo push FORÇADO (vai sobrescrever o repositorio remoto)...
git push -u origin main --force

echo.
echo ========================================
echo ✅ SUCESSO! Repositorio resetado e enviado!
echo ========================================
echo.
echo ✅ Historico limpo (sem credenciais)
echo ✅ Primeiro commit criado
echo ✅ Push feito para o GitHub
echo.
echo Verificacoes finais:
echo   git log --oneline
echo   git ls-files ^| findstr ".env"
echo.
pause
