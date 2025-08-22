# Auth Service Demo

A demo authentication and authorization service using **Spring Boot 3**, **Spring Security 6.1+**, and **JWT**.

## Features
- User authentication with Spring Security
- JWT token generation and validation
- Role-based authorization
- REST API with secure endpoints
- Unit and integration tests included
- CI/CD via GitHub Actions

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Security 6.1+
- JWT (JJWT)
- Maven

## Getting Started

### Prerequisites
- Java 17+
- Maven 3+
- Environment variable `JWT_SECRET` (Base64, 256 bits or more)

Gere uma chave Base64 segura (mínimo 256 bits):
```bash
# Linux / Mac
openssl rand -base64 32

# Windows PowerShell
[Convert]::ToBase64String((1..32 | ForEach-Object {Get-Random -Maximum 256}))
```
Windows PowerShell:
```bash
setx JWT_SECRET "YOUR_BASE64_SECRET"
```
Build and Run:
```bash
# clone repository
git clone https://github.com/maike-bezerra/auth-service-demo.git
cd auth-service-demo

# build
./mvnw clean install

# run
./mvnw spring-boot:run
```
