# 🍔 FastFood Hexagonal Java 21

Projeto backend monolito com arquitetura hexagonal usando Java 21, Gradle, Spring Boot, Swagger, Docker, Testcontainers
e SonarQube.

## 🧪 Requisitos

- Docker + Docker Compose
- Java 21
- Gradle

## 🚀 Como Rodar

```bash
# 1. Compile o projeto e gere o .jar
./gradlew clean build

# 2. Suba os containers (app, db, sonar)
docker-compose up --build
```

## 🔗 Endpoints úteis

- API Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- SonarQube: [http://localhost:9000](http://localhost:9000)
- PostgreSQL: localhost:5432 (user: postgres / senha: postgres)

## 📦 Banco de dados

O banco será criado automaticamente com os dados de `resources/db/init.sql` no container `fastfood-db`.