# Star Wars API MVC Application

## Descripción
Este proyecto es una aplicación Java basada en la arquitectura MVC que consume la API de Star Wars y proporciona endpoints REST para acceder a información sobre personajes, películas, naves espaciales y vehículos. La aplicación incluye autenticación con JWT y pruebas unitarias.

## Tecnologías Utilizadas
- **Java 8**
- **Spring Boot**
- **Spring MVC**
- **Spring Security (JWT Authentication)**
- **RestTemplate**
- **Gradle**
- **Lombok**
- **Jackson (para serialización JSON)**

## Estructura del Proyecto
```
.
├── src/main/java/com/example/starwars
│   ├── config
│   │   ├── AppConfig.java
│   │   ├── JwtAuthenticationFilter.java
│   │   ├── SecurityConfig.java
│   │   ├── SwApiConfig.java
│   ├── controller
│   │   ├── AuthController.java
│   │   ├── FilmController.java
│   │   ├── PeopleController.java
│   │   ├── StarshipController.java
│   │   ├── VehicleController.java
│   ├── model
│   │   ├── film
│   │   │   ├── FilmDTO.java
│   │   │   ├── FilmListDataDTO.java
│   │   │   ├── FilmResponseDTO.java
│   │   ├── people
│   │   │   ├── PeopleListDataDTO.java
│   │   │   ├── PeopleListResponseDTO.java
│   │   │   ├── PeopleResponseDTO.java
│   │   ├── starship   
│   │   │   ├── StarshipListDTO.java
│   │   │   ├── StarshipListResponseDTO.java
│   │   │   ├── StarshipResponseDTO.java
│   │   ├── vehicle
│   │   │   ├── VehicleListDataDTO.java
│   │   │   ├── VehicleListResponseDTO.java
│   │   │   ├── VehicleResponseDTO.java
│   ├── service
│   │   ├── FilmService.java
│   │   ├── PeopleService.java
│   │   ├── StarshipService.java
│   │   ├── VehicleService.java
│   ├── utils
│   │   ├── RestTemplateUtils.java
│   │   ├── JwtUtil.java
├── build.gradle
├── README.md
```

## Configuración
### Variables de Entorno (application.properties)
```properties
swapi.people-url=https://www.swapi.tech/api/people/
swapi.film-url=https://www.swapi.tech/api/films/
swapi.starship-url=https://www.swapi.tech/api/starships/
swapi.vehicle-url=https://www.swapi.tech/api/vehicles/
```

## Endpoints Disponibles
### Autenticación
#### `POST /auth/login`

Permite autenticar un usuario y obtener un token JWT.

- **Requiere enviar el `username` en el cuerpo con formato `x-www-form-urlencoded`.**
- Respuesta:

```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
```

Ejemplo de petición con `curl`:

```sh
curl -X POST http://localhost:8080/auth/login \
     -H "Content-Type: application/x-www-form-urlencoded" \
     -d "username=testuser"
```

- **UNA VEZ REALIZADA LA AUTENTICACIÓN SE DEBE ENVÍAR BEARER TOKEN OBTENIDO EN LOS SIGUIENTES ENDPOINTS**

### Films
- **GET /films/all?page=1&size=10**: Obtiene una lista de películas paginada.
- **GET /films/{id}**: Obtiene información detallada de una película por ID.

### People
- **GET /people/all?page=1&size=10**: Obtiene una lista de personajes paginada.
- **GET /people/{id}**: Obtiene información detallada de un personaje por ID.

### Starships
- **GET /starships/all?page=1&size=10**: Obtiene una lista de naves espaciales paginada.
- **GET /starships/{id}**: Obtiene información detallada de una nave espacial por ID.

### Vehicles
- **GET /vehicles/all?page=1&size=10**: Obtiene una lista de vehículos paginada.
- **GET /vehicles/{id}**: Obtiene información detallada de un vehículo por ID.

## Seguridad y Autenticación
- **JWT (JSON Web Token)**: La autenticación se maneja con tokens JWT generados en el endpoint `/auth/login`.
- **Filtro de seguridad**: Se valida el token en cada petición protegida.

## Implementación de Servicios
Cada servicio consume la API de Star Wars a través de `RestTemplate` y está configurado en clases separadas:
- `FilmService`
- `PeopleService`
- `StarshipService`
- `VehicleService`

### Ejemplo de Consumo de API
```java
public FilmResponseDTO getFilms(int page, int size) {
    String url = UriComponentsBuilder.fromHttpUrl(swApiConfig.getFilmUrl())
            .queryParam("page", page)
            .queryParam("limit", size)
            .toUriString();

    ResponseEntity<FilmResponseDTO> response = restTemplate.exchange(
            url, HttpMethod.GET, RestTemplateUtils.createHttpEntity(), FilmResponseDTO.class);
    
    return response.getBody();
}
```

## Instalación y Ejecución
### Prerrequisitos
- Java 8
- Gradle

### Construcción y Ejecución
```sh
git clone https://github.com/tu-repo/starwars-api-mvc.git
cd starwars-api-mvc
gradle build
gradle bootRun
```

## Pruebas
El proyecto incluye pruebas unitarias e integración para validar la funcionalidad de los servicios.
```sh
gradle test
```

## Autor
**Tu Nombre** - [LinkedIn](https://www.linkedin.com/in/franco-assaneo-421194156/) - [GitHub](https://github.com/FrancoAssaneo)

