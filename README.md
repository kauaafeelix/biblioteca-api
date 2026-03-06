<div align="center">

# Biblioteca API

API REST para gerenciamento de **livros**, **usuários** e **empréstimos**.

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.11-brightgreen?style=for-the-badge&logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue?style=for-the-badge&logo=mysql)
![Maven](https://img.shields.io/badge/Maven-3.6+-red?style=for-the-badge&logo=apache-maven)

</div>

---

## Sobre

Projeto backend em **Spring Boot** com endpoints REST para operações comuns de uma biblioteca:
- Cadastro e consulta de **Livros**
- Cadastro e consulta de **Usuários**
- Registro e controle de **Empréstimos** (incluindo devolução)

> Observação: o acesso ao banco está implementado via `java.sql` (JDBC), usando a classe `Conexao` em `utils/`.

---

## Tecnologias

- Java 21
- Spring Boot 3.5.11
- Spring Web
- MySQL (via `mysql-connector-j`)
- Maven (Maven Wrapper `mvnw`)

---

## Estrutura do repositório

```text
biblioteca-api/
├── README.md
├── biblioteca/
│   ├── .gitattributes
│   ├── .gitignore
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── com/centroweg/weg/biblioteca/
│       │   │       ├── BibliotecaApplication.java
│       │   │       ├── controller/
│       │   │       │   ├── emprestimo/EmprestimoController.java
│       │   │       │   ├── livro/LivroController.java
│       │   │       │   └── usuario/UsuarioController.java
│       │   │       ├── dto/
│       │   │       │   ├── emprestimo/
│       │   │       │   ├── livro/
│       │   │       │   └── usuario/
│       │   │       ├── mapper/
│       │   │       │   ├── emprestimo/
│       │   │       │   ├── livro/
│       │   │       │   └── usuario/
│       │   │       ├── model/
│       │   │       │   ├── Emprestimo.java
│       │   │       │   ├── Livro.java
│       │   │       │   └── Usuario.java
│       │   │       ├── repository/
│       │   │       │   ├── emprestimo/
│       │   │       │   ├── livro/
│       │   │       │   └── usuario/
│       │   │       ├── service/
│       │   │       └── utils/
│       │   │           ├── Conexao.java
│       │   │           └── TesteConexao.java
│       │   └── resources/
│       │       └── application.properties
│       └── test/
│           └── java/
```

> Nota: as pastas `service/`, `repository/*`, `dto/*` e `mapper/*` existem e contêm implementações/arquivos adicionais — a árvore acima foca nos itens principais e nos controllers/entidades que definem a API.

---

## Como rodar o projeto

### Pré-requisitos
- Java 21
- MySQL 8+
- Git

### 1) Clone e entre no módulo Maven
```bash
git clone https://github.com/kauaafeelix/biblioteca-api.git
cd biblioteca-api/biblioteca
```

### 2) Configure o banco de dados

O projeto usa uma conexão configurada em código em:
`biblioteca/src/main/java/com/centroweg/weg/biblioteca/utils/Conexao.java`

Ajuste **URL**, **USER** e **PASS** conforme seu ambiente (MySQL local, senha etc.).

> Recomendado: não commitar credenciais reais. Use variáveis de ambiente ou `application.properties` no futuro.

### 3) Rode a aplicação
```bash
./mvnw spring-boot:run
```

---

## Rotas da API (atuais)

### Livro
- `POST   /livro` — cria livro
- `GET    /livro` — lista livros
- `GET    /livro/{id}` — busca por id
- `PUT    /livro/{id}` — atualiza por id
- `DELETE /livro/{id}` — remove por id

### Usuário
- `POST   /usuario` — cria usuário
- `GET    /usuario` — lista usuários
- `GET    /usuario/{id}` — busca por id
- `PUT    /usuario/{id}` — atualiza por id
- `DELETE /usuario/{id}` — remove por id

### Empréstimo
- `POST   /emprestimo` — cria empréstimo
- `GET    /emprestimo` — lista empréstimos
- `GET    /emprestimo/{id}` — busca por id
- `GET    /emprestimo/{usuarioId}/usuario` — lista empréstimos de um usuário
- `PUT    /emprestimo/{id}` — atualiza empréstimo
- `PUT    /emprestimo/devolucao/{id}` — registra devolução
- `DELETE /emprestimo/{id}` — remove empréstimo

---

## Testes

```bash
./mvnw test
```

---

## Autor

- **Kauã Felix** — https://github.com/kauaafeelix
