<div align="center">

# 📚 Biblioteca API

### Sistema de Gerenciamento de Biblioteca com Spring Boot

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.11-brightgreen?style=for-the-badge&logo=spring)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-red?style=for-the-badge&logo=apache-maven)](https://maven.apache.org/)

<p align="center">
  <a href="#-sobre">Sobre</a> •
  <a href="#-funcionalidades">Funcionalidades</a> •
  <a href="#-tecnologias">Tecnologias</a> •
  <a href="#-começando">Começando</a> •
  <a href="#-uso">Uso</a> •
  <a href="#-contribuindo">Contribuindo</a>
</p>

![Biblioteca Banner](https://img.shields.io/badge/STATUS-EM%20DESENVOLVIMENTO-yellow?style=for-the-badge)

</div>

---

## 🎯 Sobre

**Biblioteca API** é uma aplicação REST moderna desenvolvida com Spring Boot para gerenciar operações de uma biblioteca. O projeto oferece uma solução completa e escalável para controle de acervo, empréstimos e usuários.

> 💡 **Projeto desenvolvido como atividade educacional para aprendizado do ecossistema Spring**

## ✨ Funcionalidades

<table>
  <tr>
    <td>📖</td>
    <td><b>Gerenciamento de Livros</b></td>
    <td>Cadastro, consulta, atualização e remoção de livros</td>
  </tr>
  <tr>
    <td>👥</td>
    <td><b>Controle de Usuários</b></td>
    <td>Gestão completa de usuários da biblioteca</td>
  </tr>
  <tr>
    <td>📋</td>
    <td><b>Sistema de Empréstimos</b></td>
    <td>Controle de empréstimos e devoluções</td>
  </tr>
  <tr>
    <td>🔍</td>
    <td><b>Busca Avançada</b></td>
    <td>Pesquisa de livros por diversos critérios</td>
  </tr>
  <tr>
    <td>📊</td>
    <td><b>Relatórios</b></td>
    <td>Geração de relatórios de acervo e movimentação</td>
  </tr>
</table>

## 🛠️ Tecnologias

Este projeto foi construído com as seguintes tecnologias:

<div align="center">

| Tecnologia | Versão | Descrição |
|:----------:|:------:|:---------:|
| ☕ **Java** | 21 | Linguagem de programação |
| 🍃 **Spring Boot** | 3.5.11 | Framework principal |
| 🌐 **Spring Web** | - | Desenvolvimento REST API |
| ⚡ **Spring DevTools** | - | Hot reload e produtividade |
| 🗄️ **MySQL** | 8.0+ | Banco de dados relacional |
| 📦 **Maven** | 3.6+ | Gerenciador de dependências |

</div>

## 🚀 Começando

### 📋 Pré-requisitos

Antes de começar, você precisa ter instalado em sua máquina:

```bash
☕ Java JDK 21 ou superior
🗄️ MySQL 8.0 ou superior  
📦 Maven 3.6 ou superior
🔧 Git
```

### 💾 Instalação

Siga os passos abaixo para configurar o ambiente de desenvolvimento:

#### 1️⃣ Clone o repositório

```bash
git clone https://github.com/kauaafeelix/biblioteca-api.git
cd biblioteca-api/biblioteca
```

#### 2️⃣ Configure o Banco de Dados

Crie o banco de dados no MySQL:

```sql
CREATE DATABASE biblioteca CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

#### 3️⃣ Configure as variáveis de ambiente

Crie ou edite o arquivo `src/main/resources/application.properties`:

```properties
# 🌟 Configurações da Aplicação
spring.application.name=biblioteca

# 🗄️ Configurações do Banco de Dados
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# 🔧 Configurações do JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# 🚀 Configurações do Servidor
server.port=8080
server.error.include-message=always
```

#### 4️⃣ Instale as dependências

```bash
./mvnw clean install
```

#### 5️⃣ Execute a aplicação

```bash
./mvnw spring-boot:run
```

🎉 **Pronto!** A aplicação estará rodando em `http://localhost:8080`

---

## 📱 Uso

### 🔌 Endpoints da API

> 🚧 **Em construção** - Os endpoints serão documentados conforme o desenvolvimento avança

```http
GET    /api/v1/livros          # Lista todos os livros
GET    /api/v1/livros/{id}     # Busca livro por ID
POST   /api/v1/livros          # Cadastra novo livro
PUT    /api/v1/livros/{id}     # Atualiza livro
DELETE /api/v1/livros/{id}     # Remove livro
```

### 📝 Exemplo de Request

```json
POST /api/v1/livros
Content-Type: application/json

{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "isbn": "978-0132350884",
  "anoPublicacao": 2008,
  "categoria": "Tecnologia"
}
```

---

## 📂 Estrutura do Projeto

```
biblioteca-api/
│
├── 📁 biblioteca/
│   ├── 📁 src/
│   │   ├── 📁 main/
│   │   │   ├── 📁 java/
│   │   │   │   └── 📁 com/centroweg/weg/biblioteca/
│   │   │   │       ├── 📄 BibliotecaApplication.java
│   │   │   │       ├── 📁 controller/
│   │   │   │       ├── 📁 service/
│   │   │   │       ├── 📁 repository/
│   │   │   │       ├── 📁 model/
│   │   │   │       └── 📁 config/
│   │   │   └── 📁 resources/
│   │   │       └── 📄 application.properties
│   │   └── 📁 test/
│   │       └── 📁 java/
│   └── 📄 pom.xml
│
├── 📄 README.md
└── 📄 .gitignore
```

---

## 🧪 Testes

Execute os testes automatizados:

```bash
# Executar todos os testes
./mvnw test

# Executar com relatório de cobertura
./mvnw test jacoco:report
```

---

## 📦 Build & Deploy

### 🏗️ Gerar arquivo JAR

```bash
./mvnw clean package -DskipTests
```

O arquivo será gerado em: `target/biblioteca-0.0.1-SNAPSHOT.jar`

### ▶️ Executar o JAR

```bash
java -jar target/biblioteca-0.0.1-SNAPSHOT.jar
```

---

## 🤝 Contribuindo

Contribuições são sempre bem-vindas! 🎉

1. Faça um Fork do projeto
2. Crie uma Branch para sua Feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m '✨ Adiciona nova funcionalidade'`)
4. Push para a Branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request

### 📌 Convenção de Commits

- ✨ `:sparkles:` - Nova funcionalidade
- 🐛 `:bug:` - Correção de bug
- 📚 `:books:` - Documentação
- 🎨 `:art:` - Formatação/Estilo
- ♻️ `:recycle:` - Refatoração
- 🚀 `:rocket:` - Performance
- ✅ `:white_check_mark:` - Testes

---

## 📝 Roadmap

- [x] Configuração inicial do projeto
- [x] Configuração do banco de dados
- [x] Implementação dos models
- [x] Criação dos repositories
- [x] Desenvolvimento dos services
- [x] Implementação dos controllers
- [ ] Documentação com Swagger
- [ ] Implementação de testes unitários
- [ ] Implementação de testes de integração
- [ ] Sistema de autenticação JWT
- [ ] Deploy em produção

---

## 📄 Licença

Este projeto está sob licença livre para uso educacional e pessoal.

---

## 👨‍💻 Autor

<div align="center">
  <a href="https://github.com/kauaafeelix">
    <img src="https://github.com/kauaafeelix.png" width="100px" style="border-radius: 50%"/>
  </a>
  
  **Kauã Felix**
  
  [![GitHub](https://img.shields.io/badge/-GitHub-181717?style=flat-square&logo=github)](https://github.com/kauaafeelix)
  
</div>

---

<div align="center">

### ⭐ Se este projeto foi útil, considere dar uma estrela!

</div>
