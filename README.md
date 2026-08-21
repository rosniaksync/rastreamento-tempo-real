# 🛰️ Rastreamento de Entregas em Tempo Real

Sistema backend para rastreamento de entregas em tempo real, com atualização de localização via WebSocket (STOMP). Construído para resolver um problema concreto: acompanhar a posição de um entregador ao vivo, sem depender de polling ou recarregamento manual.

## 📌 Sobre o projeto

O sistema gerencia **entregadores**, **entregas** e o **histórico de localizações** de cada entrega. Um entregador envia sua posição periodicamente via WebSocket; o servidor salva cada atualização no banco (preservando o trajeto completo) e distribui a nova posição em tempo real para todos os clientes inscritos naquela entrega específica.

## 🚀 Tecnologias

- **Java 17** + **Spring Boot**
- **Spring Data JPA / Hibernate** — persistência
- **PostgreSQL** — banco de dados
- **Flyway** — versionamento de schema
- **WebSocket + STOMP** — comunicação em tempo real
- **Docker** + **Docker Compose** — containerização
- **JUnit 5** + **Mockito** — testes unitários
- **Swagger / OpenAPI** — documentação da API
- **GitHub Actions** — pipeline de CI/CD
- **AWS** (ECS, ECR, RDS) — infraestrutura em produção

## 🏗️ Arquitetura

O sistema segue arquitetura em camadas (Controller → Service → Repository), com DTOs dedicados para entrada e saída, mapeadores próprios (evitando expor entidades JPA diretamente na API) e exceptions personalizadas para tratamento de erros de negócio.

```
Controller  → recebe requisição HTTP / mensagem WebSocket
Service     → regras de negócio
Repository  → acesso ao banco de dados
Mapper      → conversão Entity ↔ DTO
```

### Modelagem

- **Entregador** → possui várias **Entregas** (`@OneToMany` / `@ManyToOne`)
- **Entrega** → possui várias **Localizações**, formando o histórico do trajeto
- **Localização** é um registro imutável: cada atualização gera um novo ponto, nunca é editada — preservando o histórico real do percurso

### Regras de negócio

- Um entregador não pode ser excluído se tiver entregas em `PENDENTE` ou `EM_ROTA`
- Uma entrega não pode ser excluída enquanto estiver em andamento
- Toda entrega nasce com status `PENDENTE`; todo entregador nasce `DISPONIVEL`

## 🔌 WebSocket — como funciona

```
Entregador envia posição      →  /app/entregas/{id}/localizacao
Servidor salva no banco       →  nova Localizacao vinculada à entrega
Servidor distribui a posição  →  /topic/entregas/{id}
Clientes inscritos recebem    →  atualização em tempo real
```

Cada entrega tem seu próprio "canal" de broadcast — um cliente só recebe atualizações da entrega que está acompanhando.

## 🗺️ Demonstração

O projeto inclui um cliente HTML de teste com mapa interativo (Leaflet), que exibe o marcador do entregador se movendo em tempo real conforme as localizações chegam via WebSocket — incluindo um modo de simulação automática de movimento.

## ▶️ Como rodar localmente

Pré-requisitos: Docker e Docker Compose.

```bash
docker-compose up --build
```

Isso sobe o banco PostgreSQL, aplica as migrations do Flyway automaticamente e inicia a aplicação em `http://localhost:8080`.

### Documentação da API

Com a aplicação rodando, acesse a pagina:

```
http://localhost:8080/swagger-ui/index.html
```

## ✅ Testes

```bash
mvn test
```

Cobertura focada nas regras de negócio críticas (validação de exclusão de entregadores e entregas), usando mocks para isolar as dependências externas.

## ☁️ Deploy e CI/CD

A aplicação é containerizada e implantada na AWS:

- **ECR** — armazenamento da imagem Docker
- **RDS** — banco PostgreSQL gerenciado
- **ECS (Fargate)** — orquestração dos containers

O pipeline de CI/CD, definido em `.github/workflows/deploy.yml`, é disparado a cada push na branch `main` e executa automaticamente:

1. Testes automatizados
2. Build da imagem Docker
3. Push para o ECR
4. Atualização do serviço no ECS (deploy automático)

## 📂 Estrutura do projeto

```
src/main/java/.../
├── controller/       # Controllers REST e WebSocket
├── service/          # Regras de negócio
├── repository/       # Acesso a dados (Spring Data JPA)
├── mapper/           # Conversão Entity ↔ DTO
├── dto/               # Objetos de request/response
├── model/             # Entidades JPA
├── enums/             # Enums de domínio (status)
└── exception/         # Exceptions personalizadas

src/main/resources/
└── db/migration/      # Scripts Flyway
```

## 🔭 Próximos passos

- Geocodificação reversa (coordenadas → endereço legível)
- Cálculo de distância e tempo estimado de chegada (ETA)
- Notificações de mudança de status via mensageria