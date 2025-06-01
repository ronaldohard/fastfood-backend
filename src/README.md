# 🍔 FastFood Hexagonal Java 22

Projeto backend monolito com arquitetura hexagonal usando Java 22, Maven, Spring Boot, Swagger, Docker, Testcontainers
e SonarQube.

## 🧪 Requisitos

- Docker + Docker Compose
- Java 22
- Maven

## 🚀 Como Rodar

```bash
# 1. Clone o projeto
https://github.com/ronaldohard/fastfood-backend.git
# 2. Entre na pasta docker
cd docker
# 3. Gere as imagens e suba o container com as instancias de db, app e sonar 
docker-compose up --build
```

## 🔗 Endpoints úteis

- API Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- SonarQube: [http://localhost:9000](http://localhost:9000)
- PostgreSQL: localhost:5432 (user: postgres / senha: postgres)

## 📦 Banco de dados

O banco será criado automaticamente com os dados de `sql/init.sql` no container `fastfood-db`.

## 📋 Documentação DDD

https://miro.com/app/board/uXjVIDhyBL0=/?share_link_id=759980337877