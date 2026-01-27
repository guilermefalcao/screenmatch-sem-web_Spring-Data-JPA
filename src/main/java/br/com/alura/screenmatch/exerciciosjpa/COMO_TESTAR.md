# 🚀 GUIA RÁPIDO - Testar Exercícios JPA

## ✅ Como Executar

### 1. Rodar a aplicação
```
Run → ScreenmatchApplication
```

### 2. Escolher opção 5 no menu
```
5 - Testar Exercícios JPA (Produto, Categoria, Pedido)
```

### 3. Ver resultado no console
```
✅ Produto salvo: Produto{id=1, nome='Notebook Dell', preco=3500.0}
✅ Categoria salva: Categoria{id=1, nome='Eletrônicos'}
✅ Pedido salvo: Pedido{id=1, data=2024-01-15}
```

---

## 🔍 Verificar no Banco (DBeaver)

```sql
SELECT * FROM produtos;
SELECT * FROM categorias;
SELECT * FROM pedidos;
```

---

## 📝 Não precisa de Postman!

Este exercício **NÃO é uma API REST**, então:
- ❌ Não precisa de Postman
- ❌ Não tem endpoints HTTP
- ✅ Testa direto pelo menu da aplicação
- ✅ Verifica dados no DBeaver

---

## 📚 Documentação Completa

Veja: `README_EXERCICIOS_JPA.md`
