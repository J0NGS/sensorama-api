# Sensorama API

API para o jogo sensorama
---

## 📁 Arquitetura de Pastas

```
beeHelp-api/
├── .mvn/                      # Maven Wrapper
├── src/
│   ├── main/
│   │   ├── java/br/com/starter/
│   │   │   ├── application/
│   │   │   │   ├── api/             # Controllers REST
│   │   │   │   └── useCase/         # Casos de uso (Service Layer)
│   │   │   ├── domain/
│   │   │   │   ├── auth/            # Entidade Auth e serviço
│   │   │   │   ├── user/            # Entidade User, UserService, repositório
│   │   │   │   ├── role/            # Entidade Role, RoleService, RoleRepository
│   │   │   │   ├── privilege/       # Entidade Privilege, PrivilegeService
│   │   │   │   ├── profile/         # Entidade Profile, ProfileService
│   │   │   │   ├── address/         # Entidade Address, AddressService
│   │   │   │   └── common/          # Entidades comuns (ex: AuditableEntity)
│   │   │   └── infrastructure/
│   │   │       ├── config/          # Configurações de security, CORS, hierarquia de papéis
│   │   │       │   ├── security/        # Configurações de segurança (JWT, CORS)
│   │   │       │   └── boot/            # Arquivos de inicialização
│   │   │       ├── exceptions/      # exceções personalizadas
│   │   │       ├── jwt/             # Geração e validação de JWT
│   │   │       └── services/utils/  # Utils: sanitização, validação de documentos
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       ├── application-prod.properties
│   │       └── db/migration/        # Scripts Flyway (V1__initial_schema.sql)
│   └── test/
│       └── java/br/com/starter/     # Testes de integração
├── target/                   # Build output
├── docker-compose.yml
├── Dockerfile               # Dockerfile da API (com multi-stage build)
├── mvnw, mvnw.cmd           # Maven Wrapper
├── pom.xml
└── .gitignore
```

---

## 🛠 Tecnologias e Frameworks

- **Java 21**
- **Spring Boot 3.4.0**
- **Maven**
- **Spring Data JPA + PostgreSQL**
- **Flyway** (migração de schema)
- **Spring Security + JWT (jjwt)**
- **Hibernate Validator + Jakarta Validation**
- **Jackson (data/hora - jsr310)**
- **Lombok**
- **Docker + Docker Compose**
- **Hibernate Envers** (auditoria de entidades)
---

## 🚀 Como Executar com Docker Compose

### Pré-requisitos:
- Docker e Docker Compose instalados

### Passos:

1. Compile o projeto localmente (opcional, se quiser pré-validar o build):
   ```bash
   ./mvnw clean package -DskipTests
   ```

2. Inicie os containers da API e do PostgreSQL:
   ```bash
   docker-compose up --build
   ```

3. Acesse a aplicação:
   ```
   http://localhost:8080/sensorama/api/
   ```

> A aplicação será executada com o perfil `dev` por padrão e usará as variáveis de ambiente definidas no `docker-compose.yml`.

---

## 🔗 Endpoints Principais

### Autenticação (público)
- `POST /sensorama/api/public/users/login`  

### Usuários (admin)
- `POST   /sensorama/api/public/users/register`(público)
- `PUT    /sensorama/api/users/{id}`  
- `PATCH  /sensorama/api/users/{id}/update-password?newPassword=...`  
- `PATCH  /sensorama/api/users/{id}/update-username?newUsername=...`  
- `PATCH  /sensorama/api/users/{id}/privileges/{privilegeId}/add`  
- `PATCH  /sensorama/api/users/{id}/privileges/{privilegeId}/remove`  
- `PATCH  /sensorama/api/users/{id}/update-role/{roleId}`  
- `DELETE /sensorama/api/users/{id}`  

### Perfis
- `PATCH /sensorama/api/profiles/{id}/update-name?newName=...`  
- `PATCH /sensorama/api/profiles/update-name?newName=...`  

### Funções (roles)
- `GET    /sensorama/api/roles/{id}/privileges`  
- `POST   /sensorama/api/roles/{id}/privileges`  
- `DELETE /sensorama/api/roles/{id}/privileges`  

### Privilégios
- `POST   /sensorama/api/privileges?name=...`  
- `GET    /sensorama/api/privileges`  
- `PATCH  /sensorama/api/privileges/{id}/signature-revoked?isSignatureRevoked=...`  
- `DELETE /sensorama/api/privileges/{id}`  

---

## 📌 Observações

- O backend se comunica com o banco via `SPRING_DATASOURCE_URL` usando o nome do serviço Docker `postgres`.
- O volume `postgres_data` garante persistência dos dados mesmo após parar os containers.
- O container da API usa um build otimizado com JDK 21 (etapa de build + JRE leve).

---
