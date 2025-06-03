# 🍔 FastFood - Totem de Pedidos

Sistema acadêmico de pedidos para lanchonete via totem.
---

### 👨‍💻 Tecnologias utilizadas

- Java 22
- Maven (v3.9.9)
- Spring Boot 3 (v3.2.4)
- Spring Data
- Spring WebFlux (restclient)
- PostgreSQL (v15)
- Docker & Docker Compose
- MapStruct (v1.6.3)
- Lombok (v1.18.30)
- Mockito (v5.10.0)
- TestContainer (v1.19.1)
- Swagger (springdoc-openapi v2.1.0)

---

### 📚 DDD

https://miro.com/app/board/uXjVIDhyBL0=/?share_link_id=759980337877

---

### 📦 Requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- (opcional sem docker) [Java 22+](https://openjdk.org/projects/jdk/22/) e [Maven](https://maven.apache.org/)

---

## 🚀 Como Rodar

```bash
# 1. Clone o projeto
https://github.com/ronaldohard/fastfood-backend.git

# 2. Entre na pasta docker
cd docker

# 3. Gere as imagens e suba o container com as instancias de db, app e sonar 
docker-compose up --build
```

## 🔗 Endpoints publicados

- API Swagger: [http://localhost:8080/swagger](http://localhost:8080/swagger)

### 🛠️ Configurações da aplicação (Banco de Dados)

As propriedades estão configuradas no `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/fastfood
    username: postgres
    password: postgres
```

- **Host:** `localhost`
- **Porta:** `5432`
- **Usuário:** `postgres`
- **Senha:** `postgres`

Os scripts SQL são carregados automaticamente na inicialização (pastas `docker/resources/db/init/01_schema.sql`
e `02_data.sql`).

---

#### 📬 Coleção Postman (opcional)

Incluímos um arquivo `/postman/SOAT11 - FastFood (Grupo 5).postman_collection.json`.

- Importe no [Postman](https://www.postman.com/)
- Teste endpoints do FLUXO:
  - Criação de Cliente
  - Buscar cliente por CPF
  - CRUD de Produtos
  - Buscar Produtos por Categoria
  - Fake Checkout QRCODE / Confirmação
  - Listar os pedidos

---

### 🗏️ Fluxo principal (Totem de Pedido)

1. Cliente escolhe itens por categoria (hambúrguer, acompanhamento, bebida, sobremesa)
2. Visualiza resumo
3. Gera QR Code para pagamento
4. Após confirmação, pedido é finalizado

---

### 📁 Estrutura - Arquitetura Hexagonal

```
├── backend/                         # Aplicação Spring Boot
│   └── src/main/java/
│       └── br/com/fiap/postech/grupo5/fastfood/
│           ├── adapter/
│           │   ├── inbound/web/
│           │   │   ├── controller/
│           │   │   └── mappers/
│           │   └── outbound/
│           │       ├── client/
│           │       ├── entity/
│           │       │   ├── client/
│           │       │   ├── ingredient/
│           │       │   ├── order/
│           │       │   ├── pagamento/
│           │       │   ├── product/
│           │       │   └── enums/
│           │       └── repositories/
│           ├── application/
│           │   ├── dto/
│           │   └── service/
│           └── infrastructure/
│               ├── config/
│               └── handler/
│           └── FastFoodApplication.java
│   └── resources/
│       ├── application.yml
│       └── json.mercadopago/
├── docker/
│   ├── docker-compose.yml
│   ├── Dockerfile
│   └── resources/db/init/          # Scripts SQL
│       ├── 01_schema.sql
│       └── 02_data.sql
└── README.md
```

### ✅ Futuras melhorias (opcional)

- Integração real com Mercado Pago
- Painel administrativo para pedidos
- Notificações em tempo real via WebSocket

### 👥 Participantes do Grupo

| 👤 Nome                         | 🎓 RM  | 💬 Discord         | 📧 Email                      |
|---------------------------------|--------|--------------------|-------------------------------|
| Gabriel Giangiulio Cravo Zamana | 364225 | gabrielzamana      | gabrielgiangiulio@outlook.com |
| Milenna Cristina Nogueira Costa | 363739 | mih_cnc00          | milennacosta16@outlook.com    |
| Raquel Aparecida Teixeira       | 363656 | raquelat           | raquelteixeira507@gmail.com   |
| Ronaldo Ferreira Prates         | 363353 | twitch.tv/hard_aoe | ronaldohard@gmail.com         |
| Samuel Videira dos Santos       | 363405 | samuel.videira     | samuel.videira@gmail.com      |