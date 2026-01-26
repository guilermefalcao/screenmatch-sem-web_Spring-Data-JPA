# Comandos para Limpar Proxy no Windows

## No PowerShell (Execute como Administrador):

```powershell
# Limpar proxy do sistema
netsh winhttp reset proxy

# Limpar variáveis de ambiente de proxy
[Environment]::SetEnvironmentVariable("HTTP_PROXY", $null, "User")
[Environment]::SetEnvironmentVariable("HTTPS_PROXY", $null, "User")
[Environment]::SetEnvironmentVariable("NO_PROXY", $null, "User")
[Environment]::SetEnvironmentVariable("http_proxy", $null, "User")
[Environment]::SetEnvironmentVariable("https_proxy", $null, "User")
```

## No CMD (Execute como Administrador):

```cmd
netsh winhttp reset proxy
```

## Verificar se proxy está configurado:

```powershell
# Ver configuração atual
netsh winhttp show proxy

# Ver variáveis de ambiente
echo %HTTP_PROXY%
echo %HTTPS_PROXY%
```

## Limpar proxy do Maven (se estiver usando):

Edite o arquivo: `C:\Users\seu_usuario\.m2\settings.xml`

Comente ou remova a seção `<proxies>`:

```xml
<!--
<proxies>
  <proxy>
    ...
  </proxy>
</proxies>
-->
```

## Limpar proxy das configurações do Windows:

1. Abra: Configurações > Rede e Internet > Proxy
2. Desative "Usar servidor proxy"
3. Desative "Detectar configurações automaticamente"

## Após executar os comandos:

1. Feche e reabra o terminal
2. Reinicie o VS Code
3. Execute o projeto novamente
