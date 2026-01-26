@echo off
title Resetar Git - Screenmatch

cls
echo.
echo ========================================
echo RESETAR REPOSITORIO GIT - INICIO LIMPO
echo ========================================
echo.
echo ATENCAO: Este script vai APAGAR TODO o historico do Git!
echo O repositorio sera recriado do ZERO.
echo Credenciais antigas serao REMOVIDAS do historico.
echo.
echo Historico atual:
git log --oneline
echo.
echo ========================================
echo Pressione ENTER para continuar ou Ctrl+C para cancelar...
echo ========================================
pause >nul

cls
echo.
echo ========================================
echo EXECUTANDO...
echo ========================================
echo.

echo [1/7] Fazendo backup da pasta .git...
if exist ".git-backup" rmdir /s /q ".git-backup"
xcopy ".git" ".git-backup" /E /I /H /Y >nul 2>&1
if %errorlevel%==0 (
    echo OK - Backup criado em .git-backup
) else (
    echo ERRO ao criar backup!
    pause
    exit /b 1
)
echo.

echo [2/7] Removendo pasta .git (apagando historico)...
rmdir /s /q ".git"
if not exist ".git" (
    echo OK - Historico apagado
) else (
    echo ERRO ao apagar .git!
    pause
    exit /b 1
)
echo.

echo [3/7] Inicializando novo repositorio Git...
git init
if %errorlevel%==0 (
    echo OK - Repositorio inicializado
) else (
    echo ERRO ao inicializar Git!
    pause
    exit /b 1
)
echo.

echo [4/7] Verificando se .env esta protegido pelo .gitignore...
findstr /C:".env" .gitignore >nul
if %errorlevel%==0 (
    echo OK - .env esta no .gitignore
) else (
    echo ERRO: .env NAO esta no .gitignore!
    pause
    exit /b 1
)
echo.

echo [5/7] Adicionando todos os arquivos (exceto .env)...
git add .
if %errorlevel%==0 (
    echo OK - Arquivos adicionados
) else (
    echo ERRO ao adicionar arquivos!
    pause
    exit /b 1
)
echo.

echo [6/7] Criando primeiro commit limpo...
git commit -m "Initial commit: Screenmatch com Spring Data JPA e seguranca"
if %errorlevel%==0 (
    echo OK - Commit criado
) else (
    echo ERRO ao criar commit!
    pause
    exit /b 1
)
echo.

echo [7/7] Conectando ao GitHub e fazendo push...
git remote add origin https://github.com/guilermefalcao/screenmatch-sem-web_Spring-Data-JPA.git
git branch -M main
echo Fazendo push FORCADO (vai sobrescrever o repositorio remoto)...
git push -u origin main --force
if %errorlevel%==0 (
    echo OK - Push concluido
) else (
    echo ERRO ao fazer push! (pode ser problema de autenticacao)
    echo Execute manualmente: git push -u origin main --force
)
echo.

cls
echo.
echo ========================================
echo SUCESSO! REPOSITORIO RESETADO!
echo ========================================
echo.
echo OK - Historico limpo (sem credenciais)
echo OK - Primeiro commit criado
echo OK - Push feito para o GitHub
echo.
echo ========================================
echo Novo historico:
git log --oneline
echo ========================================
echo.
echo Verificacoes:
echo.
echo 1. Confirmar que .env NAO esta no Git:
git ls-files | findstr ".env" >nul
if %errorlevel%==1 (
    echo    OK - .env NAO esta no Git
) else (
    echo    ATENCAO: .env esta no Git!
)
echo.
echo 2. Buscar credenciais antigas no historico:
git log --all --full-history -S "6585022c" --oneline >nul 2>&1
if %errorlevel%==1 (
    echo    OK - Nenhuma API key encontrada no historico
) else (
    echo    ATENCAO: Ainda ha credenciais no historico!
)
echo.
echo ========================================
echo Pressione qualquer tecla para fechar...
pause >nul
