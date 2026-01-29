# Student Service - Микросервисная система записи на факультативы

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-3.9.0-blue.svg)](https://kafka.apache.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue.svg)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED.svg)](https://www.docker.com/)

## Описание

Student Service — это микросервисная система для записи студентов на университетские факультативы (electives) с автоматическим подбором преподавателей. Система построена на основе **событийно-ориентированной архитектуры (Event-Driven Architecture)** с использованием Apache Kafka для асинхронного взаимодействия между сервисами.

## Архитектура
```
┌─────────────────────┐         Kafka Topics          ┌─────────────────────────┐
│                     │                               │                         │
│  Registration       │  ──── teacher-search ────►    │  Registration           │
│  Service            │                               │  Processor              │
│                     │  ◄──── teacher-found ─────    │                         │
│  (Port: 8080)       │                               │  (Port: 8081)           │
│                     │                               │                         │
└────────┬────────────┘                               └────────────┬────────────┘
         │                                                         │
         │                                                         │
         ▼                                                         ▼
┌─────────────────────┐                               ┌─────────────────────────┐
│  PostgreSQL         │                               │  PostgreSQL             │
│  registration_      │                               │  registration_          │
│  service_db         │                               │  processor_db           │
│  (Port: 5432)       │                               │  (Port: 5433)           │
└─────────────────────┘                               └─────────────────────────┘
```

### Компоненты системы

| Компонент | Описание | Порт |
|-----------|----------|------|
| **registration-service** | Основной сервис для работы с заявками на факультативы | 8080 |
| **registration-processor** | Сервис обработки заявок и поиска преподавателей | 8081 |
| **common-libs** | Общая библиотека с DTO, событиями и enum'ами | - |
| **Kafka** | Брокер сообщений | 9092 (external), 9094 (internal) |
| **Kafka UI** | Веб-интерфейс для мониторинга Kafka | 8090 |
| **PostgreSQL (registration-service)** | БД для хранения заявок | 5432 |
| **PostgreSQL (registration-processor)** | БД с информацией о преподавателях | 5433 |

## Hexagonal Architecture (Ports & Adapters)

Проект реализован с использованием **гексагональной архитектуры**, обеспечивающей чёткое разделение бизнес-логики от инфраструктурных деталей:
```
src/main/java/org/registrationservice/
├── application/           # Application Layer (Use Cases)
│   ├── mapper/           # MapStruct маперы для событий
│   └── service/          # Сервисы приложения
├── domain/               # Domain Layer (Core Business Logic)
│   ├── model/            # Доменные модели
│   └── port/
│       ├── in/           # Входящие порты (интерфейсы use cases)
│       └── out/          # Исходящие порты (интерфейсы репозиториев)
└── infrastructure/       # Infrastructure Layer (Adapters)
    ├── adapter/
    │   ├── in/
    │   │   ├── kafka/    # Kafka Consumer адаптеры
    │   │   └── web/      # REST контроллеры
    │   └── out/
    │       ├── kafka/    # Kafka Producer адаптеры
    │       └── persistence/  # JPA репозитории
    └── config/           # Конфигурации (Kafka, etc.)
```

## Поток данных

1. **Создание заявки** — клиент отправляет POST-запрос на создание факультатива
2. **Публикация события** — `registration-service` публикует `TeacherSearchEvent` в топик `teacher-search`
3. **Обработка** — `registration-processor` получает событие и ищет преподавателя по предмету
4. **Ответ** — `registration-processor` публикует `TeacherFoundEvent` в топик `teacher-found`
5. **Обновление статуса** — `registration-service` обновляет заявку данными о преподавателе

### Статусы заявки (ElectiveStatus)

| Статус | Описание |
|--------|----------|
| `SUBMITTED` | Заявка создана |
| `APPROVAL_PENDING` | Ожидает подбора преподавателя |
| `SCHEDULED` | Преподаватель найден, занятие назначено |
| `REJECTED` | Преподаватель не найден |

### Доступные предметы (UniversitySubject)

- `MATH` — Математика
- `ALGORITHMS` — Алгоритмы
- `ENGLISH` — Английский язык
- `PHYSICS` — Физика
- `PHILOSOPHY` — Философия

## Технологический стек

- **Java 21** — основной язык разработки
- **Spring Boot 4.0.1** — фреймворк для микросервисов
- **Spring Data JPA** — работа с базами данных
- **Spring Kafka** — интеграция с Apache Kafka
- **Apache Kafka 3.9.0** (KRaft mode) — брокер сообщений
- **PostgreSQL 16** — реляционная база данных
- **Liquibase** — миграции базы данных (registration-processor)
- **MapStruct 1.6.3** — маппинг между объектами
- **Lombok 1.18.38** — сокращение boilerplate-кода
- **Docker & Docker Compose** — контейнеризация
- **Maven** — система сборки

## Требования

- **Java 21+**
- **Docker** и **Docker Compose**
- **Maven 3.9+** (или использовать встроенный Maven Wrapper)

## Быстрый старт

### 1. Клонирование репозитория
```bash
git clone <repository-url>
cd student-service
```

### 2. Запуск через Docker Compose (рекомендуется)
```bash
# Сборка и запуск всех сервисов
docker-compose up --build

# Или в фоновом режиме
docker-compose up --build -d
```

### 3. Проверка работоспособности

После запуска сервисы будут доступны:

- **Registration Service API**: http://localhost:8080
- **Kafka UI**: http://localhost:8090

## API Endpoints

### Registration Service (порт 8080)

#### Создать заявку на факультатив
```http
POST /api/elective
Content-Type: application/json

{
  "subject": "MATH"
}
```

**Ответ (201 Created):**
```json
{
  "subject": "MATH",
  "status": "APPROVAL_PENDING"
}
```

#### Получить список доступных предметов
```http
GET /api/elective/available
```

**Ответ:**
```json
["MATH", "ALGORITHMS", "ENGLISH", "PHYSICS", "PHILOSOPHY"]
```

#### Получить информацию о факультативе по предмету
```http
GET /api/elective/{subject}
```

**Пример:**
```http
GET /api/elective/math
```

**Ответ (200 OK):**
```json
{
  "subject": "MATH",
  "date": "2024-01-15T10:00:00",
  "teacherName": "Иван Петров",
  "status": "SCHEDULED"
}
```

## Локальная разработка

### Запуск инфраструктуры без сервисов
```bash
# Запустить только Kafka и PostgreSQL
docker-compose up kafka kafka-ui registration-service-db registration-processor-db -d
```

### Сборка проекта
```bash
# Сборка всех модулей
./mvnw clean install

# Сборка без тестов
./mvnw clean install -DskipTests
```

### Запуск сервисов локально
```bash
# Registration Service
cd registration-service
../mvnw spring-boot:run

# Registration Processor (в другом терминале)
cd registration-processor
../mvnw spring-boot:run
```

### Конфигурация

Переменные окружения для локального запуска:

| Переменная | Значение по умолчанию | Описание |
|------------|----------------------|----------|
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://localhost:5432/postgres` | URL базы данных |
| `SPRING_DATASOURCE_USERNAME` | `postgres` | Пользователь БД |
| `SPRING_DATASOURCE_PASSWORD` | `root` | Пароль БД |
| `SPRING_KAFKA_BOOTSTRAP_SERVERS` | `localhost:9092` | Адрес Kafka |

## Структура проекта
```
student-service/
├── common-libs/                    # Общая библиотека
│   └── src/main/java/org/commonlibs/event/
│       ├── ElectiveStatus.java     # Enum статусов заявки
│       ├── UniversitySubject.java  # Enum предметов
│       ├── TeacherSearchEvent.java # Событие поиска преподавателя
│       └── TeacherFoundEvent.java  # Событие результата поиска
│
├── registration-service/           # Сервис заявок
│   ├── Dockerfile
│   └── src/main/java/org/registrationservice/
│       ├── application/            # Use Cases
│       ├── domain/                 # Бизнес-логика
│       └── infrastructure/         # Адаптеры (Web, Kafka, JPA)
│
├── registration-processor/         # Сервис обработки
│   ├── Dockerfile
│   └── src/main/java/org/registrationprocessor/
│       ├── application/            # Use Cases
│       ├── domain/                 # Бизнес-логика
│       └── infrastructure/         # Адаптеры (Kafka, JPA)
│
├── docker-compose.yml              # Оркестрация контейнеров
└── pom.xml                         # Родительский POM
```

## Kafka Topics

| Topic | Producer | Consumer | Payload |
|-------|----------|----------|---------|
| `teacher-search` | registration-service | registration-processor | `TeacherSearchEvent` |
| `teacher-found` | registration-processor | registration-service | `TeacherFoundEvent` |

### Мониторинг Kafka

Откройте **Kafka UI** по адресу http://localhost:8090 для просмотра:
- Топиков и сообщений
- Consumer Groups
- Состояния брокера

## База данных

### Registration Service DB (порт 5432)

Таблица `electives` создаётся автоматически через Hibernate (`ddl-auto: create-drop`).

| Колонка | Тип | Описание |
|---------|-----|----------|
| id | UUID | Первичный ключ |
| subject | VARCHAR | Предмет |
| date | TIMESTAMP | Дата занятия |
| teacher_name | VARCHAR | Имя преподавателя |
| status | VARCHAR | Статус заявки |

### Registration Processor DB (порт 5433)

Таблица `teachers` управляется через **Liquibase** миграции.

| Колонка | Тип | Описание |
|---------|-----|----------|
| id | BIGINT | Первичный ключ (auto-increment) |
| name | VARCHAR(255) | Имя преподавателя |
| subject | VARCHAR(50) | Предмет |
| possible_time | TIMESTAMP | Доступное время |

**Предзагруженные данные:**

| Преподаватель | Предмет | Время |
|---------------|---------|-------|
| Иван Петров | MATH | 2024-01-15 10:00 |
| Мария Сидорова | ENGLISH | 2024-01-16 14:30 |
| Алексей Иванов | PHYSICS | 2024-01-17 09:00 |
| Ольга Николаева | ALGORITHMS | 2024-01-18 11:45 |




## Пример сценария тестирования

```bash
# 1. Получить список предметов
curl http://localhost:8080/api/elective/get/available-subjects

# 2. Создать заявку на математику
curl -X POST http://localhost:8080/api/elective/create \
  -H "Content-Type: application/json" \
  -d '{"subject": "MATH"}'

# 3. Подождать обработки (1-2 секунды) и проверить статус
curl http://localhost:8080/api/elective/get/by-subject/math

# 4. Попробовать предмет без преподавателя
curl -X POST http://localhost:8080/api/elective/create \
  -H "Content-Type: application/json" \
  -d '{"subject": "PHILOSOPHY"}'

# Статус будет REJECTED, так как преподаватель философии не добавлен
curl http://localhost:8080/api/elective/get/by-subject/philosophy
```

## Остановка сервисов
```bash
# Остановить и удалить контейнеры
docker-compose down

# Остановить и удалить вместе с данными (volumes)
docker-compose down -v
```

## Лицензия

MIT License
