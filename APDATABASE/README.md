# APDATABASE: MySQL Database Initialization

## Overview
The APDATABASE module sets up a MySQL database for the web service application. It includes a Dockerfile for containerized deployment and an initialization SQL script to configure the database schema and populate initial data.

## Key Features
1. **Containerized Deployment**:
   - Uses a MySQL 8.0 base image to provide a standardized database environment.

2. **Preloaded Data**:
   - Automatically initializes the database with tables and sample data using the provided SQL script.

3. **Configuration**:
   - Environment variables enable customization of database credentials and settings.

## Folder Structure
```
APDATABASE/
├── Dockerfile             # Docker configuration for MySQL
├── data/
│   └── db_aproV9.sql      # SQL script for initializing the database
```

## Dockerfile
The `Dockerfile` specifies the configuration for the MySQL container:

```dockerfile
FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=APDATA
ENV MYSQL_USER=apdata
ENV MYSQL_PASSWORD=apdata

COPY ./data/db_aproV9.sql /docker-entrypoint-initdb.d/

EXPOSE 3006
```

### Key Environment Variables
- `MYSQL_ROOT_PASSWORD`: Password for the root user.
- `MYSQL_DATABASE`: Name of the default database to create.
- `MYSQL_USER`: Username for the application.
- `MYSQL_PASSWORD`: Password for the application user.

## SQL Initialization Script
The SQL script `db_aproV9.sql` performs the following tasks:

1. **Database Creation**:
   ```sql
   CREATE DATABASE IF NOT EXISTS APDATA;
   USE APDATA;
   ```

2. **Table Definitions**:
   - Defines schema for entities like `admin`, `cours`, `etudiant`, `reponse`, and more.
   ```sql
   CREATE TABLE `admin` (
       `id` bigint(20) NOT NULL
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
   ```

3. **Sample Data**:
   - Inserts example rows for tables such as `cours`, `etudiant`, and `roles`.
   ```sql
   INSERT INTO `cours` (`id`, `nom_cours`, `forme_id`) VALUES
   (1, 'Spring boot basics', NULL),
   (2, 'Advanced JPA', NULL);
   ```

4. **Relationships and Constraints**:
   - Establishes foreign keys to maintain data integrity.
   ```sql
   ALTER TABLE `etudiant`
       ADD CONSTRAINT `FK8r3ygi23xihnm6jbtxoyrmlrj` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);
   ```

## Running the Database Container
### Prerequisites
- Docker installed on your system.

### Steps to Run
1. Build the Docker image:
   ```bash
   docker build -t mysql-apdata .
   ```

2. Start the container:
   ```bash
   docker run -p 3006:3306 --name mysql-container -d mysql-apdata
   ```

3. Verify the container is running:
   ```bash
   docker ps
   ```

4. Connect to the database:
   ```bash
   mysql -u apdata -p -h 127.0.0.1 -P 3006
   ```

## Schema Overview
### Tables
1. **`admin`**:
   - Stores admin user data.
2. **`cours`**:
   - Represents courses and their associated data.
3. **`etudiant`**:
   - Details about students enrolled in courses.
4. **`utilisateur`**:
   - General user data including roles and credentials.

### Relationships
- **`etudiant` ↔ `cours`**:
   - Many-to-Many relationship managed by the `etudiant_cours` table.
- **`utilisateur` ↔ `roles`**:
   - Many-to-Many relationship via `utilisateur_roles`.

## Ports
- **3006**: MySQL server port exposed for external access.

## Additional Notes
- The `db_aproV9.sql` script is automatically executed during container initialization, ensuring the database is preconfigured when the container starts.
- To modify the database schema or data, update the SQL script and rebuild the Docker image.

## Troubleshooting
- **Error: Port Already in Use**
  - Ensure no other MySQL instance is running on port 3006.
- **Connection Issues**
  - Verify Docker network settings and environment variable configurations.
