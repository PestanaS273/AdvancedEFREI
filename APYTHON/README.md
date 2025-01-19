# APYTHON API: Data Processing Service

## Overview
The Python API is a FastAPI application designed to process Excel files and update the database with student and teacher data. It supports asynchronous operations and integrates with SQLAlchemy for ORM functionalities. The API is part of the web service ecosystem and provides endpoints for uploading and processing data files.

You will find the excel files inside the excel_files direcotry.

## Folder Structure
```
APYTHON/
├── main.py                  # Entry point for the FastAPI application
├── database_class.py        # SQLAlchemy models
├── excel_analysis_etudiant.py  # Data processing logic for students
├── excel_analysis_prof.py      # Data processing logic for teachers
├── requirements.txt         # Dependencies
└── .env                     # Environment variables
```

## Key Features
1. **Excel File Upload**:
   - Endpoints for uploading Excel files for students and teachers.

2. **Database Updates**:
   - Processes uploaded files and updates the database with the parsed information.

3. **Asynchronous Background Tasks**:
   - Performs file processing in the background, ensuring the API remains responsive.

4. **CORS Middleware**:
   - Configured to allow cross-origin requests, enabling integration with other services.

## Configuration
Environment variables are stored in a `.env` file and loaded using `python-dotenv`. Example:

```
DB_USER=apdata
DB_PASSWORD=apdata
DB_HOST=mysql
DB_NAME=APDATA
DB_PORT=3306
FRONTEND_URL="http://localhost:4173"
```

## Endpoints
### **Student Data Processing**
- **POST /analyze-excel-etudiant**
  - Uploads and processes a student Excel file.
  - Starts a background task for data analysis.
  - **Payload**: `multipart/form-data` (Excel file)


- **GET /status-etudiant**
  - Fetches the progress status of the student data processing task.

  Example Response:
  ```json
  {
      "status": {
          "step": 2,
          "total_steps": 3,
          "percentage": 60,
          "message": "Updating Database"
      }
  }
  ```

### **Teacher Data Processing**
- **POST /analyze-excel-teacher**
  - Uploads and processes a teacher Excel file.
  - Starts a background task for data analysis.

- **GET /status-teacher**
  - Fetches the progress status of the teacher data processing task.

## Database Schema
The API interacts with the following tables:
- **`utilisateur`**: Stores user information.
- **`etudiant`**: Specific details for students.
- **`cours`**: Course information.
- **`etudiant_cours`**: Relationship between students and courses.
- **`role`**: User roles (e.g., `etudiant`).
- **`utilisateur_roles`**: Maps users to their roles.

## Dockerfile
The API is containerized for deployment using Docker:

```dockerfile
FROM python:3.11-slim
WORKDIR /app
COPY requirements.txt .
RUN pip install --no-cache-dir --upgrade -r requirements.txt
COPY . .
EXPOSE 8000
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]
```

## Running the API
### Locally
1. Install dependencies:
   ```bash
   pip install -r requirements.txt
   ```

2. Run the application:
   ```bash
   uvicorn main:app --reload
   ```

3. Access the API at `http://localhost:8000`.

### Using Docker
1. Build the Docker image:
   ```bash
   docker build -t python-api .
   ```

2. Run the container:
   ```bash
   docker run -p 8000:8000 python-api
   ```

3. Access the API at `http://localhost:8000`.

## Dependencies
Defined in `requirements.txt`:
```
fastapi
uvicorn
pandas
sqlalchemy
pymysql
python-dotenv
openpyxl
python-multipart
cryptography
pytest
pytest-asyncio
pytest-mock
```