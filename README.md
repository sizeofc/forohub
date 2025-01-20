# ForoHub 🚀

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)
![Java](https://img.shields.io/badge/Java-17-orange.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)
![License](https://img.shields.io/badge/License-MIT-purple.svg)

## 📋 Descripción

ForoHub es una API RESTful robusta desarrollada como parte de la especialización en Backend Java + Spring Boot de Alura Latam en colaboración con Oracle Next One Education. El proyecto implementa un sistema completo de gestión de foros, siguiendo las mejores prácticas de desarrollo y arquitectura de software.

## ✨ Características Principales

- Arquitectura hexagonal para un código limpio y mantenible
- Implementación completa de principios SOLID
- Sistema robusto de autenticación y autorización con Spring Security
- Documentación detallada de la API
- Gestión sofisticada de errores HTTP
- Operaciones CRUD completas
- Sistema de migraciones con Flyway
- Validaciones exhaustivas con Bean Validation
- Paginación eficiente de datos

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Security**
- **MySQL 8**
- **Flyway**
- **JWT (JSON Web Token)**
- **Bean Validation**
- **Maven**
- **JUnit 5**
- **Swagger/OpenAPI**

## 🚀 Configuración y Despliegue

### Prerrequisitos

- Java 17 o superior
- MySQL 8.0 o superior
- Maven 3.8+

### Variables de Entorno

Crea un archivo `.env` en la raíz del proyecto con las siguientes variables:

```properties
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_contraseña
JWT_FOROHUB=tu_clave_secreta
```

### Configuración de la Base de Datos

El archivo `application.properties` está configurado con:

```properties
spring.application.name=forohub
spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:root}
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true

forohub.security.secret=${JWT_FOROHUB:123456}
```

### Instalación y Ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/usuario/forohub.git
   cd forohub
   ```

2. Instala las dependencias:
   ```bash
   mvn clean install
   ```

3. Ejecuta las migraciones:
   ```bash
   mvn flyway:migrate
   ```

4. Inicia la aplicación:
   ```bash
   mvn spring-boot:run
   ```

## 📚 Documentación de la API

La documentación completa de la API está disponible en:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## 🔒 Seguridad

ForoHub implementa un sistema robusto de seguridad utilizando Spring Security con:

- Autenticación basada en JWT
- Control de acceso basado en roles
- Protección contra ataques CSRF
- Encriptación de contraseñas con BCrypt

## 🤝 Contribución

Las contribuciones son bienvenidas. Por favor, sigue estos pasos:

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add: AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request


## 📬 Contacto

Jonatan Atencio - [@Linkedin](https://www.linkedin.com/in/jonatanatencio/) - aa.jonatan@gmail.com

Link del proyecto: [https://github.com/sizeofc/forohub](https://github.com/sizeofc/forohub)