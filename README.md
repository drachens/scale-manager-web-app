# Balanza App Web - v1.0.0

Este proyecto es una aplicación Java Spring Boot para la gestión de balanzas. La versión 1.0.0 incluye la implementación inicial de servicios, controladores, validadores y pruebas unitarias.

## 📦 Características principales

- CRUD básico para la entidad `Scale`.
- Validación de datos mediante `javax.validation`.
- Manejo de errores global con `GlobalExceptionHandler`.
- Pruebas unitarias de servicios y controladores con JUnit 5 y Mockito.
- Persistencia en base de datos PostgreSQL (configurable).

## 🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- JUnit 5
- Mockito

## 📂 Estructura del proyecto

- `ScaleController`: Controlador REST para la entidad `Scale`.
- `ScaleService` y `ScaleServiceImpl`: Lógica de negocio.
- `ScaleRepository`: Repositorio JPA para acceso a datos.
- `GlobalExceptionHandler`: Gestión global de excepciones.
- `ValidatorConfig`: Configuración de validador.
- `application.properties`: Configuración del entorno.
- Tests:
    - `ScaleControllerTest`
    - `ScaleServiceImplTest`

## ⚙️ Configuración

Asegúrate de tener una base de datos PostgreSQL corriendo y las credenciales correctas configuradas en el archivo `application.properties`.

```properties
# PostgreSQL Configuración
spring.datasource.url=jdbc:postgresql://localhost:5432/scaleDB
spring.datasource.username=marsol
spring.datasource.password=123456
