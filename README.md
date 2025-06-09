# Doctor Appointment Booking System (Microservices)

This project is a **Doctor Appointment Booking System** built using **Spring Boot** and follows the **Microservices Architecture**. It demonstrates how multiple services can be connected to form a complete system, including managing doctor appointments and handling patient data.

---

## Project Overview

The system consists of multiple microservices:

1. **Doctor Service** – Handles the management of doctors and their details.  
2. **Appointment Service** – Manages patient appointments with doctors.  
3. **Config Server** – Centralized configuration server for microservices.  
4. **Eureka Server** – Service discovery server to allow microservices to discover and communicate with each other.  
5. **API Gateway** – A single entry point to access all services.  
6. **Circuit Breaker** – Handles faults in communication between services and provides fallback solutions.

---

## Technologies Used

- **Spring Boot**
- **Spring Cloud** (Eureka, Config Server, API Gateway, Circuit Breaker)
- **JPA (Java Persistence API)** for database interactions
- **H2** for the relational database

---

## How It Works

### 1. Doctor Service

- Provides endpoints for managing doctor data.
- Supports adding, updating, and viewing doctor information.

### 2. Appointment Service

- Handles appointment creation, updating, and viewing for patients.
- Interacts with a MySQL database to store appointment details including patient name, doctor ID, and appointment time.

### 3. API Gateway

- Acts as a front-facing gateway that routes requests to the appropriate microservice.
- Simplifies access to the system by abstracting multiple microservices behind a single entry point.

### 4. Eureka Server

- Used for service discovery.
- Allows each microservice to register itself and discover other microservices dynamically for communication.

### 5. Circuit Breaker

- Ensures that if one microservice fails, the system continues to operate using fallback methods.

---

## Database Schema

### Appointment Table

The `appointment` table stores details of appointments made by patients.

```sql
CREATE TABLE appointment (
    id INT PRIMARY KEY,
    doctor_id INT,
    patient_name VARCHAR(100),
    appointment_time DATETIME
);
```

#### Example Data

```sql
INSERT INTO appointment (id, doctor_id, patient_name, appointment_time)
VALUES (1, 1, 'Rajesh', '2025-06-10T10:00:00');

INSERT INTO appointment (id, doctor_id, patient_name, appointment_time)
VALUES (2, 2, 'Meena', '2025-06-11T14:30:00');

INSERT INTO appointment (id, doctor_id, patient_name, appointment_time)
VALUES (3, 3, 'Vikram', '2025-06-12T09:15:00');
```

---

### Doctor Table

The `doctor` table stores the details of doctors available in the system.

```sql
CREATE TABLE doctor (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    specialization VARCHAR(100),
    phone_number VARCHAR(15)
);
```

#### Example Data

```sql
INSERT INTO doctor (id, name, specialization, phone_number)
VALUES (1, 'Dr. Rajesh Kumar', 'Cardiologist', '1234567890');

INSERT INTO doctor (id, name, specialization, phone_number)
VALUES (2, 'Dr. Meena Gupta', 'Neurologist', '0987654321');

INSERT INTO doctor (id, name, specialization, phone_number)
VALUES (3, 'Dr. Vikram Sharma', 'Orthopedic', '1122334455');
```

---

## Sample API Request

### To Create a New Appointment

**Endpoint:** `POST /appointments`

**Sample Request Body:**

```json
{
  "doctorId": 5,
  "patientName": "John Doe",
  "appointmentTime": "2025-06-10T10:30:00"
}
```

---

## How to Run the Project

### Step 1: Clone the Repository

```bash
git clone https://github.com/your-repository.git
```

### Step 2: Open in Your IDE

Navigate to the project directory and open it in your preferred IDE (e.g., IntelliJ, Eclipse).

### Step 3: Setup Dependencies

Ensure the following are installed:

- **Maven**: Used to build the project.

### Step 4: Run the Services

Start each service in the following order:

1. **Eureka Server**
2. **Doctor Service**
3. **Appointment Service**
4. **API Gateway**
5. **Circuit Breaker**

### Step 5: Test the Endpoints

Use **Postman** or any HTTP client to test the following:

- `GET /appointments` – Fetch all appointments.
- `POST /appointments` – Create a new appointment (using the sample request body above).
