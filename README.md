# ByteBank - Sistema Bancário com JDBC

Projeto de estudo desenvolvido durante o curso **Java e JDBC: trabalhando com um banco de dados** da Alura. Implementa um sistema bancário simples com operações de CRUD de contas, usando **JDBC puro**, **MySQL** e **HikariCP** para pool de conexões.

---

## Funcionalidades

| Opção | Descrição |
|-------|-----------|
| 1 | Listar contas abertas |
| 2 | Abrir nova conta (número, nome, CPF, e-mail) |
| 3 | Encerrar conta logicamente (soft delete) |
| 4 | Consultar saldo |
| 5 | Realizar saque |
| 6 | Realizar depósito |
| 7 | Realizar transferência entre contas |

---

## Tecnologias

- **Java 17**
- **Maven** (build e dependências)
- **MySQL 8** (banco de dados)
- **MySQL Connector/J 8.0.33** (driver JDBC)
- **HikariCP 7.1.0** (connection pool)
- **JDBC API** (acesso a dados nativo)

---

## Estrutura do Projeto

```
src/main/java/br/com/alura/bytebank/
├── BytebankApplication.java    # Menu interativo (CLI)
├── ConnectionFactory.java      # Factory de conexões JDBC
├── domain/
│   ├── RegraDeNegocioException.java
│   ├── cliente/
│   │   ├── Cliente.java
│   │   └── DadosCadastroCliente.java
│   └── conta/
│       ├── Conta.java              # Entidade
│       ├── ContaDAO.java           # Data Access Object (JDBC)
│       ├── ContaService.java       # Regras de negócio
│       └── DadosAberturaConta.java # DTO de entrada
```

---

## Pré-requisitos

- **Java 17+** instalado e no PATH
- **Maven 3.8+** instalado
- **MySQL Server 8** rodando localmente (porta 3306)

---

## Configuração do Banco de Dados

### 1. Crie o schema e a tabela

```sql
CREATE DATABASE IF NOT EXISTS byte_bank;
USE byte_bank;

CREATE TABLE IF NOT EXISTS conta (
    numero         INT PRIMARY KEY,
    saldo          DECIMAL(19,2) NOT NULL DEFAULT 0.00,
    cliente_nome   VARCHAR(100) NOT NULL,
    cliente_cpf    VARCHAR(14) NOT NULL,
    cliente_email  VARCHAR(100) NOT NULL,
    esta_ativa     BOOLEAN NOT NULL DEFAULT TRUE
);
```

> **Atenção**: a coluna de status deve se chamar **`esta_ativa`** (com `a` final) — é o nome usado nas queries do `ContaDAO`.

### 2. Ajuste as credenciais (se necessário)

Em `ConnectionFactory.java`:

```java
// Altere user/password se seu MySQL tiver credenciais diferentes
jdbc:mysql://localhost:3306/byte_bank?user=root&password=3112
```

---

## Como Executar

### Opção 1: Via Maven (recomendado)

```bash
# Compila e roda direto
mvn compile exec:java -Dexec.mainClass=br.com.alura.bytebank.BytebankApplication
```

### Opção 2: JAR executável

```bash
mvn clean package
java -cp target/bytebank-1.0-SNAPSHOT.jar br.com.alura.bytebank.BytebankApplication
```

### Opção 3: Pela IDE

- Abra como projeto Maven no IntelliJ IDEA / Eclipse / VS Code
- Rode a classe `BytebankApplication.main()`

---

## Exemplos de Uso

```
BYTEBANK - ESCOLHA UMA OPÇÃO:
1 - Listar contas abertas
2 - Abertura de conta
3 - Encerramento de conta
4 - Consultar saldo de uma conta
5 - Realizar saque em uma conta
6 - Realizar depósito em uma conta
7 - Realizar transferencia
8 - Sair

> 2
Digite o número da conta: 12345
Digite o nome do cliente: Maria Silva
Digite o cpf do cliente: 123.456.789-00
Digite o email do cliente: maria@email.com
Conta aberta com sucesso!

> 1
Contas cadastradas:
Conta{numero='12345', saldo=0.00, titular=Cliente{nome='Maria Silva', cpf='123.456.789-00', email='maria@email.com'}}
```

---

## Conceitos Praticados

- **JDBC nativo**: `Connection`, `PreparedStatement`, `ResultSet`
- **PreparedStatement** contra SQL Injection
- **Transações**: `commit()` / `rollback()` com `setAutoCommit(false)`
- **Connection Pool** com HikariCP
- **DAO Pattern** (Data Access Object)
- **Service Layer** com regras de negócio
- **Exception handling** customizada (`RegraDeNegocioException`)
- **Soft delete** (`esta_ativa = false`) vs hard delete
- **BigDecimal** para valores monetários
- **Records/DTOs** imutáveis para entrada de dados

---

## Melhorias Futuras (Ideias)

- [ ] Migrar para **JPA/Hibernate** ou **Spring Data JPA**
- [ ] Adicionar **testes de integração** com Testcontainers
- [ ] Implementar **logging** (SLF4J + Logback)
- [ ] Externalizar configuração via `application.properties` / `yaml`
- [ ] Adicionar **migrações** com Flyway ou Liquibase
- [ ] Criar API REST com **Spring Boot** ou **Quarkus**
- [ ] Docker Compose para subir MySQL + App juntos

---

## Licença

Projeto educacional — uso livre para estudo e portfólio.

---

## Autor

Desenvolvido por **Duda** durante curso da Alura.  
Estudante de **ADS - PUC-PR EAD** (formatura prevista 2027).