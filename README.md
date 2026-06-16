# API REST de Gestión de Pedidos y Seguimiento

Este proyecto es una solución integral para la administración de pedidos, control de inventario y seguimiento de envíos, protegida mediante autenticación JWT con roles diferenciados [1], [2].

---

## Tabla de Contenidos
1. Tecnologías
2. Requisitos Previos
3. Inicio Rápido
4. Configuración de Base de Datos
5. Seguridad y Roles
6. Documentación de la API
7. Características Avanzadas

---

## 1. Tecnologías Principales

| Componente | Tecnología |
| :--- | :--- |
| Lenguaje | Java 25 |
| Framework | Spring Boot 3.2.x |
| Base de Datos | PostgreSQL 15 |
| Seguridad | JWT (JJWT) |
| Documentación | Swagger / OpenAPI |
| Despliegue | Docker + Docker Compose |

[2]

---

## 2. Requisitos Previos

* Docker y Docker Compose instalados en el sistema [3].
* Puertos **8080** y **5432** disponibles (modificar docker-compose.yml si es necesario) [3].

---

## 3. Inicio Rápido

### Despliegue del Entorno
Para poner en marcha la aplicación y la base de datos de forma automática, ejecute:

```bash
docker compose up --build -d
Comandos de Gestión Útiles
Verificar estado: docker ps
.
Ver logs del sistema: docker logs pedidos-api
.
Reiniciar aplicación: docker compose restart app
.
Limpieza y reconstrucción: docker compose down -v && docker compose up --build
.
4. Configuración de Base de Datos
El sistema configura automáticamente la base de datos al iniciar. Los datos de conexión son
:
Host: localhost
Puerto: 5432
Base de Datos: pedidosdb
Usuario: admin
Contraseña: ps123
5. Seguridad y Roles
Acceso Inicial
Para obtener el token de acceso, realice una petición al endpoint de login con las credenciales de administrador predeterminadas
,
:
Endpoint: POST /api/auth/login
Email: admin@test.com
Password: Admin123*
Matriz de Permisos
Rol
Permisos
CLIENT
Crear pedidos, ver historial propio y consultar seguimiento
.
ADMIN
Gestionar estados de pedidos y actualizar información de tracking
.
6. Documentación de la API
Una vez que el servicio esté corriendo, puede acceder a la documentación interactiva en
:
Swagger UI: http://localhost:8080/swagger-ui.html
OpenAPI JSON: http://localhost:8080/api-docs
Resumen de Endpoints
Método
Ruta
Acción
Rol
POST
/api/orders
Crear pedido
CLIENT
GET
/api/orders/my-orders
Ver mis pedidos
CLIENT
GET
/api/orders/tracking/{orderId}
Consultar tracking
CLIENT
PUT
/api/orders/{orderId}/status
Cambiar estado
ADMIN
7. Características Avanzadas
Control de Concurrencia: Se utiliza bloqueo pesimista (pessimistic locking) en la actualización de stock para garantizar la integridad de los datos y evitar sobreventa
.
Rate Limiting: El sistema permite un máximo de 60 peticiones por minuto por dirección IP. Al superarlo, se devuelve el código HTTP 429
.
Pruebas: Se incluye una colección de Postman en la carpeta /postman/postman_collection.json
.
Nota: Las tablas y datos iniciales de prueba (usuarios con roles) se generan automáticamente al arrancar la aplicación mediante un CommandLineRunner
.
