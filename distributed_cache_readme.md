# Distributed Caching in Spring Boot using Redis
A simple Spring Boot application demonstrating **true distributed caching** using a **shared Redis cache** across **multiple service instances**.

---

## 1. Overview

This project implements a distributed cache using Redis.
The goal is to show that:

- Multiple Spring Boot instances (simulating microservice pods)
- Used a **common Redis cache**
- So repeated calls **do NOT hit the database**
- Instead, subsequent calls return data from Redis instantly

This satisfies the requirement:

> **"Implementing a distributed caching solution in a Spring Boot microservice using Redis where all pods share a common cache."**

---

## 2. Project Structure

```
cache-service
├── src/main/java/com/responsive
│   ├── controller/UserController.java
│   ├── service/UserService.java
│   └── application/CacheServiceApplication.java
├── src/main/resources/application.yml
└── pom.xml
```

Redis is installed separately on the system (Windows in this case).

---

## 3. Prerequisites

### Java 17+
### Maven
### Redis installed locally (Windows)

---

## 4. Redis Installation (Windows)

Redis was installed from the official binaries.

Start Redis and verify:

```
redis-server
redis-cli
PING
```

Expected output:

```
PONG
```

Redis runs at:

```
localhost:6379
```

---

## 5. Spring Boot Configuration

`src/main/resources/application.yml`:

```yml
spring:
  application:
    name: cache-service

  cache:
    type: redis

  data:
    redis:
      host: localhost
      port: 6379
```

---

## 6. Running Multiple Instances

### **Instance 1 – Port 8081**
```
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

### **Instance 2 – Port 8082**
```
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8082"
```

---

## 8. Testing Distributed Cache

### **STEP 1 — Hit instance 1 (8081):**
```
curl http://localhost:8081/api/v1/user/1
```
Takes ~3 seconds

**Log:** Fetching from DB

---

### **STEP 2 — Hit instance 2 (8082):**
```
curl http://localhost:8082/api/v1/user/1
```
Returns instantly

**Log:** Fetching from Cache

---

### **STEP 3 — Validate Cache in Redis**
```
redis-cli
KEYS *
```

Expected:
```
"userCache::1"
```

---

## 9. Expected Outcome

* Only the first request hits the DB
* All subsequent requests (even across different ports) return from Redis
* This proves true distributed caching across multiple instances using Redis

