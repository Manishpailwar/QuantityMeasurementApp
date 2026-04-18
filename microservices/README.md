# Quantity Measurement – Microservices

Decomposition of the original Spring Boot monolith into four independently deployable Spring Boot microservices.

---

## Services at a Glance

| Service | Port | Responsibility |
|---|---|---|
| `auth-service` | **8081** | User registration, login, JWT issuance & validation |
| `length-service` | **8082** | Length conversions & arithmetic (inch, foot, yard, cm, meter) |
| `weight-volume-service` | **8083** | Weight (g, kg, lb) and Volume (ml, l, gallon) operations |
| `temperature-service` | **8084** | Temperature conversions & comparison (°C, °F, K) |

---

## Architecture

```
Client
  │
  ├─ POST /api/auth/signup|login  ──►  Auth Service (8081)
  │                                        └─ persists User to auth_service_db
  │                                        └─ returns JWT
  │
  ├─ POST /api/length/**          ──►  Length Service (8082)
  │    Authorization: Bearer JWT           └─ calls Auth Service /api/auth/validate
  │                                        └─ persists result to length_service_db
  │
  ├─ POST /api/weight/**          ──►  Weight-Volume Service (8083)
  │    Authorization: Bearer JWT           └─ calls Auth Service /api/auth/validate
  │    POST /api/volume/**                 └─ persists result to weight_volume_service_db
  │
  └─ POST /api/temperature/**     ──►  Temperature Service (8084)
       Authorization: Bearer JWT           └─ calls Auth Service /api/auth/validate
                                           └─ persists result to temperature_service_db
```

Each domain service validates incoming JWTs by calling `POST http://auth-service:8081/api/auth/validate?token=<jwt>`. No JWT secret is shared with domain services — auth is fully centralised.

---

## Quick Start

### Option A — Docker Compose (recommended)

```bash
cd microservices
docker-compose up --build
```

All four services and a MySQL 8 instance start automatically. Databases are created by `init-db.sql`.

### Option B — Run locally with Maven

Start MySQL on port 3306 (username `root`, password `manish`), then in four separate terminals:

```bash
# Terminal 1 – Auth Service (start first)
cd auth-service && mvn spring-boot:run

# Terminal 2 – Length Service
cd length-service && mvn spring-boot:run

# Terminal 3 – Weight-Volume Service
cd weight-volume-service && mvn spring-boot:run

# Terminal 4 – Temperature Service
cd temperature-service && mvn spring-boot:run
```

---

## API Reference

### Auth Service — `http://localhost:8081`

#### Sign Up
```http
POST /api/auth/signup
Content-Type: application/json

{
  "username": "alice",
  "email": "alice@example.com",
  "password": "secret123"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "alice@example.com",
  "password": "secret123"
}
```
Response: `{ "token": "<jwt>" }`

#### Validate Token (internal — called by other services)
```http
POST /api/auth/validate?token=<jwt>
```

---

### Length Service — `http://localhost:8082`

All endpoints require `Authorization: Bearer <jwt>`.

Supported units: `INCH`, `INCHES`, `FOOT`, `FEET`, `YARD`, `YARDS`, `CENTIMETER`, `CM`, `METER`, `M`

#### Convert
```http
POST /api/length/convert
{
  "sourceQuantity": { "value": 12, "unitName": "INCH" },
  "targetUnit": "FOOT"
}
```

#### Compare
```http
POST /api/length/compare
{
  "firstQuantity":  { "value": 1,  "unitName": "FOOT" },
  "secondQuantity": { "value": 12, "unitName": "INCH" }
}
```

#### Arithmetic (add / subtract / multiply / divide)
```http
POST /api/length/add
{
  "firstQuantity":  { "value": 2, "unitName": "FOOT" },
  "secondQuantity": { "value": 6, "unitName": "INCH" },
  "resultUnit": "FOOT"
}
```

#### History
```http
GET /api/length/history
GET /api/length/history/{operationType}   # CONVERT | COMPARE | ADD | SUBTRACT | MULTIPLY | DIVIDE
```

---

### Weight-Volume Service — `http://localhost:8083`

All endpoints require `Authorization: Bearer <jwt>`.

**Weight units:** `GRAM`, `G`, `KILOGRAM`, `KG`, `POUND`, `LB`, `LBS`  
**Volume units:** `MILLILITER`, `ML`, `LITER`, `L`, `GALLON`, `GALLONS`

#### Weight endpoints
```
POST /api/weight/convert
POST /api/weight/compare
POST /api/weight/add
POST /api/weight/subtract
POST /api/weight/multiply
POST /api/weight/divide
GET  /api/weight/history
GET  /api/weight/history/{operationType}
```

#### Volume endpoints
```
POST /api/volume/convert
POST /api/volume/compare
POST /api/volume/add
POST /api/volume/subtract
POST /api/volume/multiply
POST /api/volume/divide
GET  /api/volume/history
GET  /api/volume/history/{operationType}
```

Example — convert 1 gallon to liters:
```json
POST /api/volume/convert
{
  "sourceQuantity": { "value": 1, "unitName": "GALLON" },
  "targetUnit": "LITER"
}
```

---

### Temperature Service — `http://localhost:8084`

All endpoints require `Authorization: Bearer <jwt>`.

Supported units: `CELSIUS` / `C`, `FAHRENHEIT` / `F`, `KELVIN` / `K`

#### Convert
```http
POST /api/temperature/convert
{
  "sourceQuantity": { "value": 100, "unitName": "CELSIUS" },
  "targetUnit": "FAHRENHEIT"
}
```

#### Compare
```http
POST /api/temperature/compare
{
  "firstQuantity":  { "value": 0,   "unitName": "CELSIUS" },
  "secondQuantity": { "value": 273.15, "unitName": "KELVIN" }
}
```

#### History
```http
GET /api/temperature/history
GET /api/temperature/history/{operationType}   # CONVERT | COMPARE
```

---

## Swagger UI

Each service exposes an OpenAPI UI at:

| Service | URL |
|---|---|
| Auth | http://localhost:8081/swagger-ui.html |
| Length | http://localhost:8082/swagger-ui.html |
| Weight-Volume | http://localhost:8083/swagger-ui.html |
| Temperature | http://localhost:8084/swagger-ui.html |

---

## Project Structure

```
microservices/
├── docker-compose.yml
├── init-db.sql
├── auth-service/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/com/example/authservice/
│       ├── AuthServiceApplication.java
│       ├── config/SecurityConfig.java
│       ├── controller/AuthController.java
│       ├── dto/AuthDTOs.java
│       ├── entity/User.java
│       ├── exception/{GlobalExceptionHandler, UserAlreadyExistsException, InvalidCredentialsException}.java
│       ├── repository/UserRepository.java
│       ├── security/JwtService.java
│       └── service/UserManagementService.java
├── length-service/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/com/example/lengthservice/
│       ├── LengthServiceApplication.java
│       ├── controller/LengthController.java
│       ├── dto/LengthDTOs.java
│       ├── entity/LengthOperationEntity.java
│       ├── exception/{GlobalExceptionHandler, LengthMeasurementException, UnauthorizedException}.java
│       ├── repository/LengthOperationRepository.java
│       ├── security/AuthClient.java
│       ├── service/LengthService.java
│       └── util/LengthMathHelper.java
├── weight-volume-service/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/com/example/weightvolumeservice/
│       ├── WeightVolumeServiceApplication.java
│       ├── controller/{WeightController, VolumeController}.java
│       ├── dto/MeasurementDTOs.java
│       ├── entity/WeightVolumeOperationEntity.java
│       ├── exception/{GlobalExceptionHandler, MeasurementException, UnauthorizedException}.java
│       ├── repository/WeightVolumeOperationRepository.java
│       ├── security/AuthClient.java
│       ├── service/WeightVolumeService.java
│       └── util/WeightVolumeMathHelper.java
└── temperature-service/
    ├── Dockerfile
    ├── pom.xml
    └── src/main/java/com/example/temperatureservice/
        ├── TemperatureServiceApplication.java
        ├── controller/TemperatureController.java
        ├── dto/TemperatureDTOs.java
        ├── entity/TemperatureOperationEntity.java
        ├── exception/{GlobalExceptionHandler, TemperatureException, UnauthorizedException}.java
        ├── repository/TemperatureOperationRepository.java
        ├── security/AuthClient.java
        ├── service/TemperatureService.java
        └── util/TemperatureMathHelper.java
```

---

## Configuration

All services read standard Spring Boot environment variables. Override for production:

| Variable | Default | Used by |
|---|---|---|
| `SPRING_DATASOURCE_URL` | localhost MySQL | All services |
| `SPRING_DATASOURCE_USERNAME` | `root` | All services |
| `SPRING_DATASOURCE_PASSWORD` | `manish` | All services |
| `APPLICATION_SECURITY_JWT_SECRET_KEY` | (hex key) | Auth Service only |
| `APPLICATION_SECURITY_JWT_EXPIRATION` | `86400000` (24 h) | Auth Service only |
| `AUTH_SERVICE_URL` | `http://localhost:8081` | Length, Weight-Volume, Temperature |
