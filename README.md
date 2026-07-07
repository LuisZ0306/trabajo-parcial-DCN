# Trabajo Parcial: API RESTful de Soporte Técnico

Este proyecto es una API RESTful. Su objetivo es permitir a una empresa de servicios tecnológicos gestionar de manera organizada y eficiente sus solicitudes de soporte técnico de clientes.

---

## Tecnologías y Herramientas
*   **Java 21**
*   **Spring Boot 3.5.16**
*   **Spring Starter Web** (para endpoints REST)
*   **Spring Starter Validation** (para validación de DTOs)
*   **Lombok** (para reducir código repetitivo)
*   **Springdoc OpenAPI / Swagger UI** (para documentación interactiva de la API)

---

## Arquitectura del Proyecto
El proyecto está organizado en una arquitectura por capas bien definida.

```
pe.idat.rest.api.app.trabajoparcial
│
├── TrabajoParcialApplication.java    # Clase principal de arranque
│
├── controller                         # Controladores REST (Endpoints)
│   ├── ClienteController.java
│   ├── TecnicoController.java
│   └── SolicitudController.java
│
├── service                            # Interfaces de Lógica de Negocio
│   ├── ClienteService.java
│   ├── TecnicoService.java
│   └── SolicitudService.java
│   └── impl                           # Implementaciones de servicios
│       ├── ClienteServiceImpl.java
│       ├── TecnicoServiceImpl.java
│       └── SolicitudServiceImpl.java
│
├── repository                         # Repositorios en Memoria (ConcurrentHashMap)
│   ├── ClienteRepository.java
│   ├── TecnicoRepository.java
│   └── SolicitudRepository.java
│
├── model                              # Modelos de Dominio (Entidades)
│   ├── Cliente.java
│   ├── Tecnico.java
│   └── Solicitud.java
│
├── dto                                # Objetos de Transferencia Inmutables (records)
│   ├── ClienteRequestDto.java | ClienteResponseDto.java
│   ├── TecnicoRequestDto.java | TecnicoResponseDto.java
│   └── SolicitudRequestDto.java | SolicitudResponseDto.java
│
└── exception                          # Excepciones personalizadas y manejador global
    ├── RecursoNoEncontradoException.java
    ├── ReglaNegocioException.java
    └── GlobalException.java           # @RestControllerAdvice
```

---

## Instrucciones de Instalación y Ejecución

### Prerrequisitos:
*   Java Development Kit (JDK) 21 instalado.
*   Maven instalado (o usar el wrapper `mvnw` incorporado).
*   IntelliJ IDEA u otro IDE compatible con proyectos Maven.

### Pasos para Ejecutar:
1.  Abre el proyecto `trabajo-parcial` en tu IDE favorito.
2.  Importa el proyecto como un proyecto Maven (deja que el IDE descargue las dependencias de `pom.xml`).
3.  Ejecuta la clase principal `TrabajoParcialApplication.java` o ejecuta el siguiente comando en la terminal desde la carpeta del proyecto:
    ```bash
    ./mvnw spring-boot:run
    ```
4.  El microservicio se iniciará en el puerto **`8586`**.

---

## Documentación Interactiva (Swagger UI)
Una vez que la aplicación esté corriendo, puedes visualizar e interactuar directamente con todos los endpoints ingresando a la siguiente URL en tu navegador web:

 **[http://localhost:8586/swagger-ui.html](http://localhost:8586/swagger-ui.html)**

---

## Endpoints Principales (API REST)

### Gestión de Clientes (`/api/v1/clientes`)
*   `POST /api/v1/clientes` - Registrar un nuevo cliente.
*   `GET /api/v1/clientes` - Listar todos los clientes.
*   `GET /api/v1/clientes/{id}` - Obtener cliente por ID.
*   `PUT /api/v1/clientes/{id}` - Actualizar datos de un cliente.
*   `DELETE /api/v1/clientes/{id}` - Eliminar un cliente.

### Gestión de Técnicos (`/api/v1/tecnicos`)
*   `POST /api/v1/tecnicos` - Registrar un nuevo técnico.
*   `GET /api/v1/tecnicos` - Listar todos los técnicos.
*   `GET /api/v1/tecnicos/{id}` - Obtener técnico por ID.
*   `PUT /api/v1/tecnicos/{id}` - Actualizar datos de un técnico (activo/inactivo, especialidad, etc.).
*   `DELETE /api/v1/tecnicos/{id}` - Eliminar un técnico.

### Gestión de Solicitudes de Soporte (`/api/v1/solicitudes`)
*   `POST /api/v1/solicitudes` - Registrar una solicitud asignándola a un cliente y opcionalmente a un técnico activo.
*   `GET /api/v1/solicitudes` - Listar todas las solicitudes (incluye la información anidada del cliente y técnico asociado).
*   `GET /api/v1/solicitudes/{id}` - Obtener solicitud por ID.
*   `PUT /api/v1/solicitudes/{id}` - Actualizar una solicitud (cambiar su estado: `PENDIENTE`, `EN_PROCESO`, `RESUELTO`, asignar técnico, etc.).
*   `DELETE /api/v1/solicitudes/{id}` - Eliminar una solicitud.

---

## Manejo Global de Errores y Validaciones
*   Las excepciones como la no existencia de un cliente, técnico o solicitud lanzan una `RecursoNoEncontradoException`, la cual es capturada por el manejador global retornando un código HTTP `404 Not Found` estructurado en formato JSON.
*   Intentar asignar un técnico inactivo a una solicitud lanza una `ReglaNegocioException`, retornando un estado `400 Bad Request`.
*   Los datos obligatorios (DNI de 8 dígitos, teléfono de 9 dígitos, correos válidos) se validan en los DTOs de entrada, retornando errores descriptivos y estructurados bajo estado `400 Bad Request` en caso de fallos.

---

## Sugerencia de Roles del Equipo para el Informe Técnico
Para el reporte solicitado por el docente, pueden designar los roles de la siguiente manera:
1.  **Integrante 1: Arquitecto Backend / Base de Datos Temporal**
    *   *Responsabilidad:* Diseño de la arquitectura de paquetes, configuración de dependencias de Spring Boot y desarrollo de modelos (`Cliente`, `Tecnico`, `Solicitud`) y repositorios en memoria (`ConcurrentHashMap`).
2.  **Integrante 2: Especialista en Lógica de Negocio (Servicios y DTOs)**
    *   *Responsabilidad:* Creación de las interfaces e implementaciones de los servicios, definición de las reglas de negocio (ej. validar estado de técnico activo) y diseño de los `records` DTO (Request y Response) con validaciones estructuradas.
3.  **Integrante 3: Desarrollador REST API / QA & Documentación**
    *   *Responsabilidad:* Desarrollo de los controladores REST (`@RestController`), diseño de rutas jerárquicas, implementación de Swagger OpenAPI, y realización del testing completo de endpoints mediante Postman (generación de capturas de pantalla para el informe).
