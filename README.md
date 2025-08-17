
# Literalura

Literalura es una aplicación de consola desarrollada en Java y Spring Boot que permite consultar, registrar y explorar libros y autores a través de la API pública de Gutendex. El proyecto está orientado a la práctica de conceptos de persistencia, consumo de APIs, manejo de entidades y repositorios, y buenas prácticas de desarrollo en Java.

## Funcionalidades principales

- **Buscar libro por título:** Permite buscar un libro por su título utilizando la API de Gutendex y registrar el resultado en la base de datos local.
- **Listar libros registrados:** Muestra todos los libros almacenados en la base de datos.
- **Listar autores:** Muestra todos los autores registrados en la base de datos.
- **Listar autores en determinado año:** Permite consultar autores que estuvieron vivos en un año específico.
- **Listar libros en determinado idioma:** Filtra y muestra los libros registrados según el idioma seleccionado (portugués, inglés, español o francés).
- **Listar los 10 libros más descargados:** Muestra el top 10 de libros con mayor número de descargas.

## Estructura del proyecto

- `src/main/java/br/com/alura/literalura/model/` — Entidades principales: `Libro`, `Autor`, etc.
- `src/main/java/br/com/alura/literalura/repository/` — Repositorios Spring Data JPA para persistencia.
- `src/main/java/br/com/alura/literalura/service/` — Servicios para consumo de API y lógica de negocio.
- `src/main/java/br/com/alura/literalura/principal/` — Clase principal de interacción con el usuario.

## Tecnologías utilizadas
- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database (por defecto, puede configurarse otra en `application.properties`)
- Maven

## Ejecución

1. Clona el repositorio:
	```
	git clone <url-del-repositorio>
	```
2. Accede al directorio del proyecto y ejecuta:
	```
	./mvnw spring-boot:run
	```
	o en Windows:
	```
	mvnw.cmd spring-boot:run
	```
3. Sigue las instrucciones del menú en consola.

## Notas importantes
- El proyecto aún puede contener nombres de variables o métodos en portugués. Se recomienda revisar y traducir completamente a español para mantener la coherencia.
- El archivo `.gitignore` está configurado para evitar subir archivos de configuración de IDE, carpetas de compilación y otros archivos temporales.

## Autoría
Ejercicio educativo basado en desafíos de Alura Latam.

---
¡Colabora mejorando la traducción y la documentación!
