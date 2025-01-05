# Web Service Project for University Feedback : Advanced EFREI

## Introduction
This project is a comprehensive web service system designed to integrate with existing university services to streamline the process of class feedback. It provides a flexible and accessible environment for professors, students, and administrators to manage and respond to surveys efficiently. The application consists of a:

- **Frontend**: Developed in Vue.js using Vite.
- **Backend**: Implemented in Java with Spring Boot.
- **Python API**: Handles Excel file processing and database updates.
- **Database**: MySQL for structured data management.

The entire ecosystem is containerized using Docker and orchestrated with Docker Compose for seamless deployment and scalability.

### Key Features
1. **Survey Creation and Management**:
   - Administrators can create surveys for students in specific courses.
   - Surveys can be configured for anonymous or identified responses.

2. **Student and Professor Participation**:
   - Both students and professors can respond to assigned surveys.
   - Capability to view the history of completed surveys.

3. **Multilanguage Support**:
   - Includes translations for Spanish, English, French, and Japanese.
   - Additional languages can be added by modifying JSON configuration files.

4. **Automated Updates**:
   - The Python API processes Excel files to update the database with user and course information.

5. **Deployment and Implementation**:
   - The project is containerized and deployable using Docker Compose, ensuring easy setup across different environments.

### Deployment Instructions
To deploy the project, follow these steps:

1. Clone the repository and navigate to the project directory.
2. Ensure Docker and Docker Compose are installed on your system.
3. Use the provided `docker-compose.yml` file for deployment. Below is the configuration:

```yaml
docker-compose.yml
version: '3.8'
services:
  mysql:
    build: ./APDATABASE
    container_name: mysql-container
    environment:
      - MYSQL_ROOT_PASSWORD=root
      - MYSQL_DATABASE=APDATA
      - MYSQL_USER=apdata
      - MYSQL_PASSWORD=apdata
    ports:
      - "3006:3306"
    networks:
      - app-network
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
      interval: 10s
      timeout: 5s
      retries: 3

  backend: 
    build: ./APBACK
    container_name: backend-container
    depends_on:
      - mysql
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://mysql-container:3306/APDATA
      - SPRING_DATASOURCE_USERNAME=apdata
      - SPRING_DATASOURCE_PASSWORD=apdata
    ports:
      - "8095:8095"
    networks:
      - app-network

  frontend:
    build: ./APFRONT
    container_name: frontend-container
    ports:
      - "4173:4173"
    networks:
      - app-network

  python-api:
    build: ./APYTHON
    container_name: python-api-container
    depends_on:
      - frontend
      - mysql
    ports:
      - "8000:8000"
    networks:
      - app-network

networks:
  app-network:
    driver: bridge
```

4. Run the following command to build and start the containers:

   ```bash
   docker-compose up --build
   ```

5. Access the application using the following ports:
   - **Frontend**: http://localhost:4173
   - **Backend**: http://localhost:8095
   - **Python API**: http://localhost:8000

6. Verify that the services are running by checking the container logs:

   ```bash
   docker-compose logs
   ```

### Project Structure

| Component  | Description | Details |
|------------|-------------|---------|
| **Frontend**  | User interface built with Vue.js and Vite | [Frontend README](./APFRONT/README.md) |
| **Backend**   | API and business logic implemented in Spring Boot | [Backend README](./APBACK/README.md) |
| **Python API**| Processes Excel files and updates the database | [Python API README](./APYTHON/README.md) |
| **Database**  | MySQL database initialized with Docker | [Database README](./APDATABASE/README.md) |

### Troubleshooting
1. **Database Connection Issues**:
   - Ensure the MySQL service is running and accessible on port `3006`.
   - Check the environment variables in `docker-compose.yml`.

2. **Container Build Errors**:
   - Verify that the Dockerfiles in each component folder are correct and dependencies are properly defined.

3. **API or Frontend Not Responding**:
   - Check the respective container logs for errors.

```bash
   docker logs <container-name>
```

### Additional Notes
- Each component's detailed setup and functionality can be found in their respective README files.
- The project supports role-based access, ensuring secure interactions for different user roles.
- This system can be extended to include additional features, such as advanced analytics or external API integrations.

