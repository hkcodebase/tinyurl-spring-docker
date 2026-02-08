# Tinyurl Backend (tinyurl-api)

A Spring Boot backend API that demonstrates a working “tiny URL” service, along with common production-ready capabilities such as OpenAPI/Swagger docs and Actuator. The service stores URL mappings in Cassandra.

---

## Contents

- [Features](#features)
- [Tech stack](#tech-stack)
- [Project generation](#project-generation)
- [Prerequisites](#prerequisites)
- [Run locally (Maven)](#run-locally-maven)
- [Run with Docker Compose](#run-with-docker-compose)
- [Endpoints to verify](#endpoints-to-verify)
- [API documentation (Swagger / OpenAPI)](#api-documentation-swagger--openapi)
- [Actuator](#actuator)
- [OpenAPI stub generation](#openapi-stub-generation)
- [Cassandra Via docker](#cassandra-via-docker-)
- [Cassandra Connect with IntelliJ](#connect-with-intellij)
- [Cassandra Connect with cqlsh](#connect-with-cqlsh)
- [References](#references)

---

## Features

- **OpenAPI spec & Swagger UI**
  - Auto-generation support for API interfaces from an `openapi.yml`
- **Actuator** for health/metrics-style endpoints
- **Cassandra** persistence (local via Docker / Docker Compose)
- Short-code generation (6-digit) using Apache Commons Lang

---

## Tech stack

- Java + Spring Boot (Spring MVC)
- Maven
- Cassandra
- Docker / Docker Compose

---

## Project generation

This project was initially generated via Spring Initializr:

- https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.5.5&packaging=jar&jvmVersion=24&groupId=com.hk.prj&artifactId=tinyurl-api&name=tinyurl-api&description=Project%20to%20serve%20as%20tinyurl%20backend&packageName=com.hk.prj.tinyurl-api&dependencies=web

---

## Prerequisites

- Java 21
- Maven
- Docker Desktop (for Cassandra locally)

---

## Run locally (Maven)

1. Start Cassandra (see [Cassandra via Docker](#cassandra-via-docker-))
2. Build & run:
   ```bash
   mvn clean spring-boot:run
   ```

---

## Run with Docker Compose

From the project root:
```bash
docker compose up -d
```

Stop:
```bash
docker compose down
```

---

## Endpoints to verify

### When started via Maven (local Spring Boot)

- Redirect example:
  - `http://localhost:8080/api/v1/redirect/123456`
- Swagger UI:
  - `http://localhost:8080/api/v1/swagger-ui/index.html`

### When started via Docker Compose

- Redirect example:
  - `http://localhost/api/v1/redirect/123456`
- Swagger UI:
  - `http://localhost/api/v1/swagger-ui/index.html`

Example curl: `curl -i 'http://localhost:8080/api/v1/redirect/123456'`

> Note - Use the port 80 if running via Docker Compose

---

## API documentation (Swagger / OpenAPI)

Swagger UI is available at:

- `<basepath>/<context-path>/swagger-ui/index.html`

In this app (local Maven run):

- `http://localhost:8080/api/v1/swagger-ui/index.html`

---

## Actuator

Actuator is available at:

- `<basepath>/<context-path>/actuator`

In this app (local Maven run):

- `http://localhost:8080/api/v1/actuator`

---

## OpenAPI stub generation

The OpenAPI source file:

- [`src/main/resources/openapi.yml`](api/src/main/resources/openapi.yml)

The project uses the OpenAPI Generator Maven plugin (see [`pom.xml`](api/pom.xml)) to generate Spring interfaces/models from the spec.

Related dependency (see `pom.xml`):

- `org.openapitools:jackson-databind-nullable`
---

## Cassandra via docker 
> Note - when running this project via maven command

Start:
```bash
 docker pull cassandra:latest docker network create cassandra-net docker run --rm -d --name cassandra --hostname cassandra --network cassandra-net -p 9042:9042 cassandra
```

Stop:
```bash
docker stop cassandra
```

## Connect with IntelliJ

See: `cassandra-intellij.png`

## Connect with cqlsh
```bash
docker exec -it cassandra cqlsh
```


---

## References

- Install Docker Desktop:
    - https://docs.docker.com/desktop/setup/install/windows-install/
- OpenAPI Generator article:
    - https://www.baeldung.com/java-openapi-generator-server
- Docker Desktop issue on Windows (WSL required):
    - If you see: `Error code: Wsl/CallMsi/REGDB_E_CLASSNOTREG`
    - WSL releases: https://github.com/microsoft/WSL/releases/