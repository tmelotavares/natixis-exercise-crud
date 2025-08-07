# Client Management CRUD App - Natixis Challenge Exercise

This project is a full-stack CRUD application to manage clients and their addresses, built with:

- **Backend**: Java 17, Spring Boot, H2, Maven
- **Frontend**: Angular 19 (standalone APIs, reactive forms)

---

## 🌐 Features

### 🧾 Client Entity

Each client has:
- Name
- Date of Birth
- Fiscal ID (NIF)
- At least one address

### 🏠 Address Entity

Each address includes:
- Street
- Number
- Complement (optional)
- Postal Code
- Council
- District

---

## ✅ Business Rules

- Clients must be **at least 18 years old**
- Each client must have **at least one address**
- **Fiscal ID (NIF) must be unique**
- Validation errors return clear messages (e.g., `"NIF already exists"`, `"Client must have at least 18 years old"`)

---

## ⚙️ Backend Setup

### Requirements

- Java 17+
- Maven

### Run

```bash
./mvnw spring-boot:run
```

### API Docs (Swagger)

Once running, open:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🖥️ Frontend Setup

### Requirements

- Node.js (v18+ recommended)
- Angular CLI (`npm install -g @angular/cli`)

### Install & Run

```bash
cd exercise-crud
npm install
ng serve
```

Open [http://localhost:4200](http://localhost:4200)

---

## 📂 Project Structure

```
backend/
│
├── model/
├── controller/
├── service/
├── repository/
└── config/
```

```
frontend/
│
├── client-form/
├── client-list/
├── models/
└── services/
```

---

## 🧪 Tests

### Run backend tests

```bash
./mvnw test
```

Includes:
- Service and controller unit tests (JUnit 5 + Mockito)
- Business rule validation (age, NIF)

---

## 📌 Tech Stack

- Spring Boot 3.x
- H2 in-memory database
- Hibernate Validator
- Angular 19 standalone components
- Angular Reactive Forms
- Swagger for REST documentation

---
