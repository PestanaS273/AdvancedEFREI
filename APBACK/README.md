
# Backend: Spring Boot

## Overview
The backend of the application is built using Spring Boot and serves as the central hub for business logic and API endpoints. It handles user management, survey operations, and database interactions.

## Configuration
The backend uses the following configurations as defined in the `application.properties`


## Key Endpoints
The backend exposes a variety of endpoints through the `ApoRestController`. Below are some notable examples:

- **User Management**:
  - `POST /inscrire`: Registers a new user.
  - `POST /create`: Creates a new student.
  - `GET /users`: Retrieves all users.

- **Survey Management**:
  - `GET /getAllCours`: Fetches all courses.
  - `POST /saveReponse`: Saves a survey response.
  - `POST /addForme`: Adds a new survey form.

- **Statistics**:
  - `GET /getNombreEtudiantActive`: Returns the number of active students.
  
## Services and Business Logic
The `AproInitServiceImpl` class implements business logic for managing users, courses, and surveys. Highlights include:

- User roles and permissions management.
- Survey creation and linking with courses.
- Automated initialization of courses and questions for testing purposes.

## Security
The backend includes JWT-based authentication and authorization, configured in the `SecurityConfig` class. Key features include:

- Stateless authentication with token-based sessions.
- Role-based access control.
- CORS configuration to allow frontend communication.

## Create Jar File
Before creating a new jar file, the use of IntelliJ is recommended and Maven must be installed.
The used .JAR file is in the jar directory. If you want to create a new jar, run the following command :
```bash
  mvn clean install -DskipTests
```

Then, place the jar created at the Target directory to the jar directory.

The backend will be accessible at `http://localhost:8095`.
