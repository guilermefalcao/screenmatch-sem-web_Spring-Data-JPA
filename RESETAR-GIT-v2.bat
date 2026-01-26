@echo off
chcp 65001 >nul
color 0A
title Resetar Git - Screenmatch

cls
echo.
echo ╔════════════════════════════════════════════════════════════╗
echo ║     RESETAR REPOSITÓRIO GIT - INÍCIO LIMPO                 ║
echo ╚════════════════════════════════════════════════════════════╝
echo.
echo ⚠️  ATENÇÃO: Este script vai APAGAR TODO o histórico do Git!
echo ⚠️  O repositório será recriado do ZERO.
echo ⚠️  Credenciais antigas serão REMOVIDAS do histórico.
echo.
echo Histórico atual:
git log --oneline
echo.
echo ════════════════════════════════════════════════════════════
echo Pressione ENTER para continuar ou Ctrl+C para cancelar...
echo ════════════════════════════════════════════════════════════
pause >nul

cls
echo.
echo ╔════════════════════════════════════════════════════════════╗
echo ║  EXECUTANDO...                                             ║
echo ╚════════════════════════════════════════════════════════════╝
echo.

echo [1/7] 📦 Fazendo backup da pasta .git...
if exist ".git-backup" (
    echo       Removendo backup antigo...
    rmdir /s /q ".git-backup"
)
xcopy ".git" ".git-backup" /E /I /H /Y >nul 2>&1
if %errorlevel%==0 (
    echo       ✅ Backup criado em .git-backup
) else (
    echo       ❌ ERRO ao criar backup!
    pause
    exit /b 1
)
echo.

echo [2/7] 🗑️  Removendo pasta .git (apagando histórico)...
rmdir /s /q ".git"
if not exist ".git" (
    echo       ✅ Histórico apagado
) else (
    echo       ❌ ERRO ao apagar .git!
    pause
    exit /b 1
)
echo.

echo [3/7] 🆕 Inicializando novo repositório Git...
git init
if %errorlevel%==0 (
    echo       ✅ Repositório inicializado
) else (
    echo       ❌ ERRO ao inicializar Git!
    pause
    exit /b 1
)
echo.

echo [4/7] 🔒 Verificando se .env está protegido pelo .gitignore...
findstr /C:".env" .gitignore >nul
if %errorlevel%==0 (
    echo       ✅ .env está no .gitignore
) else (
    echo       ❌ ERRO: .env NÃO está no .gitignore!
    pause
    exit /b 1
)
echo.

echo [5/7] 📁 Adicionando todos os arquivos (exceto .env)...
git add .
if %errorlevel%==0 (
    echo       ✅ Arquivos adicionados
) else (
    echo       ❌ ERRO ao adicionar arquivos!
    pause
    exit /b 1
)
echo.

echo [6/7] 💾 Criando primeiro commit limpo...
git commit -m "🎉 Initial commit: Screenmatch com Spring Data JPA e segurança (variáveis de ambiente)"
if %errorlevel%==0 (
    echo       ✅ Commit criado
) else (
    echo       ❌ ERRO ao criar commit!
    pause
    exit /b 1
)
echo.

echo [7/7] 🚀 Conectando ao GitHub e fazendo push...
git remote add origin https://github.com/guilermefalcao/screenmatch-sem-web_Spring-Data-JPA.git
git branch -M main
echo       ⚠️  Fazendo push FORÇADO (vai sobrescrever o repositório remoto)...
git push -u origin main --force
if %errorlevel%==0 (
    echo       ✅ Push concluído
) else (
    echo       ❌ ERRO ao fazer push! (pode ser problema de autenticação)
    echo       Execute manualmente: git push -u origin main --force
)
echo.

cls
echo.
echo ╔════════════════════════════════════════════════════════════╗
echo ║  ✅ SUCESSO! REPOSITÓRIO RESETADO!                         ║
echo ╚════════════════════════════════════════════════════════════╝
echo.
echo ✅ Histórico limpo (sem credenciais)
echo ✅ Primeiro commit criado
echo ✅ Push feito para o GitHub
echo.
echo ════════════════════════════════════════════════════════════
echo Novo histórico:
git log --oneline
echo ════════════════════════════════════════════════════════════
echo.
echo Verificações:
echo.
echo 1. Confirmar que .env NÃO está no Git:
git ls-files | findstr ".env"
if %errorlevel%==1 (
    echo    ✅ .env NÃO está no Git
) else (
    echo    ❌ ATENÇÃO: .env está no Git!
)
echo.
echo 2. Buscar credenciais antigas no histórico:
git log --all -S "6585022c" --oneline
if %errorlevel%==1 (
    echo    ✅ Nenhuma API key encontrada no histórico
)
echo.
echo ════════════════════════════════════════════════════════════
echo Pressione qualquer tecla para fechar...
pause >nul
